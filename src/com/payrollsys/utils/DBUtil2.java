package com.bjsxt.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil2 {
	static String driver;
	static String url;
	static String user;
	static String password;
	//定义一个ThreadLocal变量，存放的是Connection
	//可以保证在同一个线程中，不同层次、不同方法都使用ThreadLocal的Connection，使用的是同一个Connection
	//JavaEE中，客户端的一个请求就是一个线程
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	//读取属性文件properties并获取内容
	static{
		//准备一个空的map，没有key-value
		Properties prop = new Properties();
		
		//读取文件，并将文件键值对存入Properties对象
		//InputStream is = new FileInputStream(new File("C:\Users\Administrator\workspace\java_empmgr2\src\conn.properties"));
		InputStream is = DBUtil2.class.getResourceAsStream("/jdbc.properties"); //classpath
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//从prop中根据key获取四个参数的值
		driver = prop.getProperty("driver");
		//driver = prop.get("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("username");
		password = prop.getProperty("password");
		
		//加载驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static  Connection getConnection(){
		Connection conn = null;
		//不是直接创建新的连接，而是首先从ThreadLocal中获取
		conn = threadLocal.get();
		//如果threadLocal中不存在，说明是当前线程第一次获取连接，需要先创建再放入threadLocal
		if(conn ==null){
			//先创建
			try{			
				//建立数据库连接			
				conn = DriverManager.getConnection(url, user, password);
			}catch(SQLException e){
				e.printStackTrace();
			}
			//再放入threadLocal
			threadLocal.set(conn);
		}
		
		return conn;
		
	}
	
	/**
	 * 关闭数据库资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeAll(ResultSet rs ,Statement stmt,Connection conn){
		//关闭数据库资源
		try {
			if(rs!=null){
				rs.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null){
				stmt.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null){
				threadLocal.set(null);//从threadLocal中移除Connection
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * DML:insert  update  delete
	 */
	public static int executeUpdate(String  sql,Object ... params) {
		Connection conn =  null;
		PreparedStatement pstmt = null;
		int n = 0;
		try{
			//获取数据库连接
			conn = getConnection();
			
			//使用手枪发送SQL命令并得到结果			
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}		
			n = pstmt.executeUpdate();
			
			
		}catch(SQLException e){
			//处理异常
			e.printStackTrace();
			//throw e;
			//抛出异常给上级（调用者）
			//throw new RuntimeException(e.toString());
			throw new MyException(e.toString());
		}finally{
			//关闭数据库资源
			//closeAll(null, pstmt, conn);
			closeAll(null, pstmt, null);//采用了业务层事务后，DAO层不关闭Connection
		}
				
		//返回数据
		return n;
	}
	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}
}
