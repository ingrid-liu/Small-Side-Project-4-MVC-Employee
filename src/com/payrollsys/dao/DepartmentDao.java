package com.payrollsys.dao;

import java.util.List;

import com.payrollsys.entity.Department;

public interface DepartmentDao {
	/**
	 * 更新部门
	 * @param dept
	 * @return
	 */
	public int update(Department dept);
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public int save(Department dept);
	
	/**
	 * 查询所有部门信息
	 * @return
	 */
	public List<Department> findAll();
	/**
	 * 删除指定编号的部门
	 * @param deptno
	 * @return
	 */
	public int delete(int deptno);
	/**
	 * 查询指定编号的部门
	 * @param deptno
	 * @return
	 */
	public Department findById(int deptno);
	
}
