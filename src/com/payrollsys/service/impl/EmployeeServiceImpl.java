package com.bjsxt.service.impl;

import java.util.Date;
import java.util.List;

import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.dao.impl.EmployeeDaoImpl;
import com.bjsxt.entity.Employee;
import com.bjsxt.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDao empDao = new EmployeeDaoImpl();
	
	@Override
	public int add(Employee emp) {
		return this.empDao.save(emp);
	}
	@Override
	public int update(Employee emp) {
		return this.empDao.update(emp);
	}
	@Override
	public List<Employee> findEmpByType(int i) {
		
		return this.empDao.findByType(i);
	}

	@Override
	public List<Employee> findAll() {
		return this.empDao.findAll();
	}

	@Override
	public List<Employee> findEmp(String empId, int deptno, int onDuty,
			Date hireDate) {
		
		return this.empDao.find(empId, deptno, onDuty, hireDate); 
	}

	@Override
	public void delete(String empId) {
		this.empDao.delete(empId);
		
	}

	@Override
	public Employee findById(String empId) {
		return this.empDao.findById(empId);
	}
	@Override
	public Employee login(String empId, String password) {
		//如何判断登录是否成功：
		//方法1：find(empId,password)  select * from employee where empid =? and password = ?
		//return this.empDao.find(empId,password);
		//方法2：分两步实现，先判断用户名是否正确，用户名不正确，登录失败，用户名正确，在判断密码
		Employee emp = this.empDao.findById(empId);
		if(emp == null){//用户名是错误的
			return null;
		}else{
			if(password!=null & password.equals(emp.getPassword())){
				return emp;
			}else{//用户名正确，密码错误
				return null;
			}
		}
		
	}

}
