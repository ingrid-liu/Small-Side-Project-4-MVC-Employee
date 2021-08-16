package com.payrollsys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payrollsys.dao.PaymentDao;
import com.payrollsys.entity.Payment;
import com.payrollsys.util.DBUtil;
import com.payrollsys.util.DBUtil2;
import com.payrollsys.util.DateUtil;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public void save(Payment pm) {
		String sql = "insert into payment values (seq_payment.nextval,?,?,?,?,?)";
		Object params  [] = {pm.getPayEmpId(),pm.getAmount(),
				new java.sql.Timestamp(pm.getPayTime().getTime()),pm.getExpId(),pm.getExpEmpId()};
		DBUtil2.executeUpdate(sql, params);
		
	}

	@Override
	public List<Object[]> findStaticsData(int type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object []> list = new ArrayList<Object[]>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			StringBuilder sql =new StringBuilder("select item.type,sum(item.amount) "
					+ " from payment pm"
					+ " join expense exp "
					+ " on pm.expid = exp.expid "
					+ " join expenseitem item "
					+ " on exp.expid = item.expid"
					+ " where 1=1");
				if(type==1){
					sql.append("and to_char(paytime,'YYYY-MM-DD HH:MI:SS') > '"+DateUtil.getNowMonthBeginTime()+"'");
				}else if(type==2){
					sql.append("and to_char(paytime,'YYYY-MM-DD HH:MI:SS') <= '"+DateUtil.getNowYearEndTime()+"'");
					sql.append("and to_char(paytime,'YYYY-MM-DD HH:MI:SS') > '"+DateUtil.getNowYearBeginTime()+"'");
					
				}else if(type==3){
					sql.append("and to_char(paytime,'YYYY-MM-DD HH:MI:SS') <= '"+DateUtil.getLastYearEndTime()+"'");
					sql.append("and to_char(paytime,'YYYY-MM-DD HH:MI:SS') > '"+DateUtil.getLastYearBeginTime()+"'");
				}
				sql.append(" group by item.type");
				sql.append( " order by item.type");
			pstmt = conn.prepareStatement(sql.toString());
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				String itemType = rs.getString(1);
				double amount = rs.getDouble(2);
				//2.将当前行各个字段的值封装到Employee对象中
				Object []  arr = {itemType,amount};
				//3.将user放入userList
				list.add(arr);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return list;
	}

}
