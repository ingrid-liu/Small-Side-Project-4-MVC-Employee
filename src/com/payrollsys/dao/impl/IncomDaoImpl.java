package com.payrollsys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.payrollsys.dao.IncomeDao;
import com.payrollsys.entity.Department;
import com.payrollsys.util.DBUtil;

public class IncomDaoImpl implements IncomeDao {

	@Override
	public List<Object[]> findStaticsData() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object []> list = new ArrayList<Object[]>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select ictype,sum(amount) from income  group by ictype");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				String icType = rs.getString(1);
				double amount = rs.getDouble(2);
				//2.将当前行各个字段的值封装到Employee对象中
				Object []  arr = {icType,amount};
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
	
	public static void main(String[] args) {
		IncomeDao  icDao = new IncomDaoImpl();
		List<Object []> list = icDao.findStaticsData();
		System.out.println(list.size());
	}

}


