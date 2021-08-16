package com.bjsxt.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.bjsxt.entity.Employee;

public interface EmployeeDao {
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 */
	public int save(Employee emp);
	
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
	public List<Employee> findByType(int type);
	
	/**
	 * 查询所有员工
	 * @param i
	 * @return
	 */
	public List<Employee> findAll();
	/**
	 * 多条件查询员工信息，不带分页
	 * @param empId  员工编号
	 * @param deptno 部门编号
	 * @param onDuty  是否在职
	 * @param hireDate 入职时间
	 * @return
	 */
	public List<Employee> find(String empId, int deptno, int onDuty,
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
}
