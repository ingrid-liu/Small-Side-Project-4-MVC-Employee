package com.payrollsys.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.payrollsys.dao.AuditingDao;
import com.payrollsys.dao.ExpenseDao;
import com.payrollsys.dao.ExpenseItemDao;
import com.payrollsys.dao.impl.AuditingDaoImpl;
import com.payrollsys.dao.impl.ExpenseDaoImpl;
import com.payrollsys.dao.impl.ExpenseItemDaoImpl;
import com.payrollsys.entity.Auditing;
import com.payrollsys.entity.Expense;
import com.payrollsys.entity.ExpenseItem;
import com.payrollsys.service.ExpenseService;
import com.payrollsys.util.DBUtil;
import com.payrollsys.util.DBUtil2;
import com.payrollsys.util.MyException;

public class ExpenseServiceImpl2 implements ExpenseService{
	@Override
	public void audit(Auditing auditing) {
		AuditingDao auditingDao = new AuditingDaoImpl();
		ExpenseDao expDao = new ExpenseDaoImpl();
		//开启事务
		Connection conn = DBUtil2.getConnection();
		try {
			conn.setAutoCommit(false);//事务不再自动结束（提交，回滚），事务并没有在此开始
			//审核通过吗
			String result = auditing.getResult();
			if("通过".equals(result)){//审核通过
				//是财务吗				
				if(auditing.getAuditor().getPosition().getPosId()==6){//是财务
					//添加支出记录
					
					//修改报销单状态					
				}else{
					//金额大于2500吗  auditing.getExp().getTotalAmount()>2500  空指针异常
					//获取报销单的金额
					int expId = auditing.getExpId();
					Expense exp2 = expDao.findById(expId);
					if(exp2.getTotalAmount()>2500){//大于2500
						//当前审核人是总裁吗
						if("gaoqi".equals(auditing.getAuditor().getEmpId())){
							//添加审核记录							
							auditingDao.save(auditing);
							//修改报销单状态
							Expense exp = new Expense();
							exp.setExpId(auditing.getExpId());						
							exp.setStatus("2");
							exp.setLastResult(auditing.getResult());
							exp.setNextAuditorId("lifuying"); //财务
							expDao.update(exp);								
						}else{
							//添加审核记录
							auditingDao.save(auditing);
							//修改报销单状态
							Expense exp = new Expense();
							exp.setExpId(auditing.getExpId());						
							exp.setStatus("1");
							exp.setLastResult(auditing.getResult());
							exp.setNextAuditorId("gaoqi"); //总裁
							//exp.setNextAuditorId(auditing.getAuditor().getMgr().getEmpId());
							expDao.update(exp);	
						}
					}else{//小于等于2500
						//添加审核记录
						auditingDao.save(auditing);
						//修改报销单状态  下个处理人：财务  状态：审核通过
						Expense exp = new Expense();
						exp.setExpId(auditing.getExpId());						
						exp.setStatus("2");
						exp.setLastResult(auditing.getResult());
						exp.setNextAuditorId("lifuying"); //财务
						expDao.update(exp);						
					}					
				}
				
			}else{//不通过，拒绝或者打回
				//添加审核记录
				auditingDao.save(auditing);
				//修改报销单状态
				Expense exp = new Expense();
				exp.setExpId(auditing.getExpId());
				if("打回".equals(auditing.getResult())){
					exp.setStatus("4");
				}else{
					exp.setStatus("3");
				}
				exp.setLastResult(auditing.getResult());
				exp.setNextAuditorId(null); //拒绝 打回 没有下个审核人
				expDao.update(exp);
				
			}
						
			//结束事务（提交或回滚）
			conn.commit();
		} catch (Exception e) {//!!! 所有异常，保存运行时异常，SQLSyntaxErrorException
			e.printStackTrace();			
			try {
				conn.rollback(); //回滚数据
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MyException(e1.toString());
			}
			throw new MyException(e.toString());//向上一层抛出异常信息
			
		}finally{
			DBUtil2.closeAll(null, null, conn);			
		}
		
	}
	
	/**
	 * 问题：如何知道报销单的编号（自增），因为报销单明细需要
	 * 	可以先获取序列的下一个值
	 *  select seq_exp.nextval from dual
	 *  
	 * 
	 * 问题2：报销单成功、明细不成功，只提交了部分数据，数据不完整
	 * 应该全部成功提交，或者一个也没有提交
	 * 原因：一个DML操作一个事务，事务在Dao层，是多个事务，而不是一个事务
	 * 
	 * 解决：将多个操作视为一个事务，此时事务要从DAO层提升到业务层
	 */
	@Override
	public void add(Expense expense) {
		//获取序列的下一个值，
		ExpenseDao expDao = new ExpenseDaoImpl();
		int expId = expDao.nextVal();
		//开启事务
		Connection conn = DBUtil2.getConnection();
		try {
			conn.setAutoCommit(false);//事务不再自动结束（提交，回滚），事务并没有在此开始
			//添加一条报销单（主单）信息
			expense.setExpId(expId);
			expDao.save(expense);//执行第一个DML操作，事务自动开始
					
			//添加多条报销单所属的明细的信息
			ExpenseItemDao itemDao = new ExpenseItemDaoImpl();
			List<ExpenseItem> itemList = expense.getItemList();
			for(int i=0;i<itemList.size();i++){
				ExpenseItem item = itemList.get(i);
				item.setExpId(expId);
				itemDao.save(item);
			}			
			//结束事务（提交或回滚）
			conn.commit();
		} catch (Exception e) {//!!! 所有异常，保存运行时异常，SQLSyntaxErrorException
			e.printStackTrace();			
			try {
				conn.rollback(); //回滚数据
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MyException(e1.toString());
			}
			throw new MyException(e.toString());//向上一层抛出异常信息
			
		}finally{
			DBUtil2.closeAll(null, null, conn);
			
		}		
	}

	@Override
	public List<Expense> getToAudit(String auditorId) {
		ExpenseDao expDao = new ExpenseDaoImpl();
		return expDao.findByAuditorId(auditorId);
	}

	

}
