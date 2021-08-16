package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.entity.Department;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Position;
import com.bjsxt.util.DBUtil;

public class EmployeeDaoImpl  implements EmployeeDao {
	@Override
	public int update(Employee emp) {
		String sql = "update employee set password=?,deptno=?,posid=?,"
				+ "mgrid=?,realname=?,sex=?,birthdate=?,hiredate=?,leavedate=?,"
				+ "onduty=?,emptype=?,phone=?,qq=?,emercontactperson=?,idcard=?"
				+ " where empid=?";
		
		java.sql.Date leaveDate2 = null;
		Date leaveDate = emp.getLeaveDate();
		if(leaveDate != null){
			leaveDate2 = new java.sql.Date(leaveDate.getTime());
		}
		
		Object [] params ={			
			emp.getPassword(),
			emp.getDept().getDeptno(), //?
			emp.getPosition().getPosId(),//?
			emp.getMgr().getEmpId(),//?
			emp.getRealName(),
			emp.getSex(),
			new java.sql.Date(emp.getBirthDate().getTime()),
			new java.sql.Date(emp.getHireDate().getTime()),
			leaveDate2,   //???
			emp.getOnDuty(),
			emp.getEmpType(),
			emp.getPhone(),
			emp.getQq(),
			emp.getEmerContactPerson(),
			emp.getIdCard(),
			emp.getEmpId()
		};
		return DBUtil.executeUpdate(sql, params);
	}
	
	@Override
	public int save(Employee emp) {
		String sql = "insert into employee values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		java.sql.Date leaveDate2 = null;
		Date leaveDate = emp.getLeaveDate();
		if(leaveDate != null){
			leaveDate2 = new java.sql.Date(leaveDate.getTime());
		}
		
		Object [] params ={
			emp.getEmpId(),
			emp.getPassword(),
			emp.getDept().getDeptno(), //?
			emp.getPosition().getPosId(),//?
			emp.getMgr().getEmpId(),//?
			emp.getRealName(),
			emp.getSex(),
			new java.sql.Date(emp.getBirthDate().getTime()),
			new java.sql.Date(emp.getHireDate().getTime()),
			leaveDate2,   //???
			emp.getOnDuty(),
			emp.getEmpType(),
			emp.getPhone(),
			emp.getQq(),
			emp.getEmerContactPerson(),
			emp.getIdCard()
		};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Employee> findByType(int type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from employee where emptype=? order by empId desc");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				String empId = rs.getString("empId");
				String realName = rs.getString("realName");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
				//2.将当前行各个字段的值封装到Employee对象中
				Employee emp = new Employee();
				emp.setEmpId(empId);
				emp.setRealName(realName);
				emp.setSex(sex);
				emp.setPhone(phone);
				//3.将user放入userList
				list.add(emp);
				
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
	public List<Employee> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			String sql ="select e.empid,e.deptno,e.posid,e.mgrid,e.realname,e.sex,e.hiredate,"
					+ " e.phone,d.deptname,p.pname,mgr.empid,mgr.realname"
					+ " from employee  e"
					+ " join dept d"
					+ " on e.deptno = d.deptno"
					+ " join position p"
					+ " on e.posid = p.posid"
					+ " join employee mgr"
					+ " on e.mgrid = mgr.empid";
			pstmt = conn.prepareStatement(sql);
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				String empId = rs.getString("empId");//??
				String realName = rs.getString("realName");//??
				String sex = rs.getString("sex");
				Date hireDate = rs.getDate("hireDate");
				String phone = rs.getString("phone");
				
				//2.将当前行各个字段的值封装到Employee对象中
				Employee emp = new Employee();
				emp.setEmpId(empId);
				emp.setRealName(realName);
				emp.setSex(sex);
				emp.setHireDate(hireDate);
				emp.setPhone(phone);
				
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				Department dept = new Department(deptno,deptName);
				emp.setDept(dept);
				
				int posId = rs.getInt("posid");
				String posName = rs.getString("pname");
				Position position = new Position(posId, posName);
				emp.setPosition(position);
				
				String mgrId = rs.getString(11);//???
				String mgrRealName= rs.getString(12);
				Employee mgr = new Employee();
				mgr.setEmpId(mgrId);
				mgr.setRealName(mgrRealName);
				emp.setMgr(mgr);				
				
				//3.将user放入userList
				list.add(emp);
				
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
	public List<Employee> find(String empId2, int deptno2, int onDuty,
			Date hireDate2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			StringBuilder sql =new StringBuilder("select e.empid,e.deptno,e.posid,e.mgrid,e.realname,e.sex,e.hiredate,"
					+ " e.phone,d.deptname,p.pname,mgr.empid,mgr.realname"
					+ " from employee  e"
					+ " join dept d"
					+ " on e.deptno = d.deptno"
					+ " join position p"
					+ " on e.posid = p.posid"
					+ " join employee mgr"
					+ " on e.mgrid = mgr.empid where 1=1 ");
			
			if(empId2!= null && !"".equals(empId2)){
				sql.append(" and e.empid like '%"+empId2+"%'");
			}
			if(deptno2 != 0){
				sql.append(" and e.deptno="+deptno2);
			}
			//if(){
				sql.append(" and e.onDuty = "+onDuty);
			//}
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(hireDate2 !=null){
				String shireDate = sdf.format(hireDate2); // "2013-12-23"
				sql.append(" and to_char(e.hiredate,'YYYY-MM-DD') >= '"+shireDate+"'");
			}
			pstmt = conn.prepareStatement(sql.toString());
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				String empId = rs.getString("empId");//??
				String realName = rs.getString("realName");//??
				String sex = rs.getString("sex");
				Date hireDate = rs.getDate("hireDate");
				String phone = rs.getString("phone");
				
				//2.将当前行各个字段的值封装到Employee对象中
				Employee emp = new Employee();
				emp.setEmpId(empId);
				emp.setRealName(realName);
				emp.setSex(sex);
				emp.setHireDate(hireDate);
				emp.setPhone(phone);
				
				int deptno = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				Department dept = new Department(deptno,deptName);
				emp.setDept(dept);
				
				int posId = rs.getInt("posid");
				String posName = rs.getString("pname");
				Position position = new Position(posId, posName);
				emp.setPosition(position);
				
				String mgrId = rs.getString(11);//???
				String mgrRealName= rs.getString(12);
				Employee mgr = new Employee();
				mgr.setEmpId(mgrId);
				mgr.setRealName(mgrRealName);
				emp.setMgr(mgr);				
				
				//3.将user放入userList
				list.add(emp);
				
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
	public void delete(String empId){
		String sql = "delete from employee where empid = ?";
		//String sql = "update set password = '123456' where empid= ?";
		Object [] params = {empId};
		DBUtil.executeUpdate(sql, params);		
	}

	@Override
	public Employee findById(String empId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee emp =  null;
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			
			pstmt = conn.prepareStatement("select * from employee where empid= ?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//1.取出当前行各个字段的值
				//String empId = rs.getString("empId");//??
				String realName = rs.getString("realName");//??
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				Date birthDate = rs.getDate("birthDate");
				Date hireDate = rs.getDate("hireDate");
				Date leaveDate = rs.getDate("leaveDate");
				int onDuty = rs.getInt("onduty");
				int empType = rs.getInt("emptype");
				String phone = rs.getString("phone");
				String qq = rs.getString("qq");
				String emerContactPerson = rs.getString("EMERCONTACTPERSON");
				String idCard = rs.getString("IDCARD");
				
				//2.将当前行各个字段的值封装到Employee对象中
				int deptno = rs.getInt("DEPTNO");
				Department dept = new Department();
				dept.setDeptno(deptno);
				
				int posId = rs.getInt("posid");			
				Position position = new Position();
				position.setPosId(posId);
				
				
				String mgrId = rs.getString("MGRID");//???
				Employee mgr = new Employee();
				mgr.setEmpId(mgrId);
					
				emp = new Employee(empId, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
				
								
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return emp;
	}

	
	
}
