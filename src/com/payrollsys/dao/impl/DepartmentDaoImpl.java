package com.payrollsys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payrollsys.dao.DepartmentDao;
import com.payrollsys.entity.Department;
import com.payrollsys.util.DBUtil;


public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public int update(Department dept) {
		String sql = "update dept set deptname=?,location=? where deptno= ?";
		Object [] params = {dept.getDeptName(),dept.getLocation(),dept.getDeptno()};
		
		return DBUtil.executeUpdate(sql, params);
	}
	
	@Override
	public int delete(int deptno) {
		String sql = "delete from dept where deptno = ?";
		Object [] params = {deptno};
		
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int save(Department dept) {
		String sql = "insert into dept values(?,?,?)";
		Object [] params = {dept.getDeptno(),dept.getDeptName(),dept.getLocation()};
		
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Department> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<Department>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from dept");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				//2.将当前行各个字段的值封装到Employee对象中
				Department dept = new Department(deptno, deptName, location);
				//3.将user放入userList
				list.add(dept);
				
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
	public Department findById(int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Department dept = null;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from dept  where deptno = ?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）		
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			if(rs.next()){
				//1.取出当前行各个字段的值
				//int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				String location = rs.getString("location");
				//2.将当前行各个字段的值封装到Employee对象中
				dept = new Department(deptno, deptName, location);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return dept;
	}

	
}
