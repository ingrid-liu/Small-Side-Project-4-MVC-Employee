package com.payrollsys.service;

import java.util.Date;
import java.util.List;

import com.payrollsys.entity.Employee;

public interface EmployeeService {
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 */
	public int add(Employee emp);
	/**
	 * 更新员工
	 * @param emp
	 * @return
	 */
	public int update(Employee emp);
	/**
	 * 查询指定类型的员工
	 * @param i
	 * @return
	 */
	public List<Employee> findEmpByType(int i);
	
	/**
	 * 查询所有员工
	 * @param i
	 * @return
	 */
	public List<Employee> findAll();
	/**
	 * 多条件查询员工，不带分页
	 * @param empId
	 * @param deptno
	 * @param onDuty
	 * @param hireDate
	 * @return
	 */
	public List<Employee> findEmp(String empId, int deptno, int onDuty,
			Date hireDate);
	/**
	 * 删除指定编号的员工
	 * @param empId
	 */
	public void delete(String empId);
	/**
	 * 查询指定编号的员工
	 * @param empId
	 * @return
	 */
	public Employee findById(String empId);
	/**
	 * 实现登录操作
	 * @param empId
	 * @param password
	 * @return
	 */
	public Employee login(String empId, String password);
}
