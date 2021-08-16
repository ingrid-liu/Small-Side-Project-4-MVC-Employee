package com.payrollsys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payrollsys.dao.ExpenseDao;
import com.payrollsys.entity.Employee;
import com.payrollsys.entity.Expense;
import com.payrollsys.util.DBUtil;
import com.payrollsys.util.DBUtil2;

public class ExpenseDaoImpl implements ExpenseDao{
	@Override
	public List<Expense> findByAuditorId(String auditorId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Expense> list = new ArrayList<Expense>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select exp.* ,emp.realName  from expense exp join employee emp  on exp.empid = emp.empid where nextauditor=?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setString(1, auditorId);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				int expId = rs.getInt("expId");
				String empId = rs.getString("empId");
				double totalAmount = rs.getDouble("totalAmount");
				Date expTime = rs.getDate("expTime");
				String expDesc = rs.getString("expDesc");
				String nextAuditorId = auditorId;
				String lastResult = rs.getString("lastResult");
				String status = rs.getString("status");
				
				String realName = rs.getString("realName");
				Employee emp =  new Employee();
				emp.setEmpId(empId);
				emp.setRealName(realName);
				//2.将当前行各个字段的值封装到Employee对象中
				Expense exp = new Expense(expId, empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status, emp, null);
				//3.将user放入userList
				list.add(exp);
				
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
	
	
	@Override
	public int nextVal() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nextVal = 0;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select seq_exp.nextval from dual");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			rs.next();
			nextVal = rs.getInt(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}		
		//7.返回数据		
		return nextVal;
	}

	@Override
	public void save(Expense exp) {
		String sql = "insert into expense values(?,?,?,?,?,?,?,?)";
		Object [] params = {exp.getExpId(),exp.getEmpId(),exp.getTotalAmount(),
				new java.sql.Date(exp.getExpTime().getTime()),exp.getExpDesc(),exp.getNextAuditorId(),
				exp.getLastResult(),exp.getStatus()};
		DBUtil2.executeUpdate(sql, params);
	}


	@Override
	public void update(Expense exp) {
		String sql ="update expense set nextauditor = ?,lastresult=?,status =? where expid=?";
		Object params [] = {exp.getNextAuditorId(),exp.getLastResult(),exp.getStatus(),exp.getExpId()};
		DBUtil2.executeUpdate(sql, params);
		
		
	}


	@Override
	public Expense findById(int expId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Expense exp = null;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from expense where expId = ?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, expId);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			if(rs.next()){
				//1.取出当前行各个字段的值
				//int expId = rs.getInt("expId");
				String empId = rs.getString("empId");
				double totalAmount = rs.getDouble("totalAmount");
				Date expTime = rs.getDate("expTime");
				String expDesc = rs.getString("expDesc");
				String nextAuditorId = rs.getString("nextAuditor");
				String lastResult = rs.getString("lastResult");
				String status = rs.getString("status");
				
				//2.将当前行各个字段的值封装到Employee对象中
				exp = new Expense(expId, empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status, null, null);
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return exp;
	}

	

}
