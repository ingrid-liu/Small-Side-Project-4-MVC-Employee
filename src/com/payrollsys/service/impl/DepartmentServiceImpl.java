package com.payrollsys.service.impl;

import java.util.List;

import com.payrollsys.dao.DepartmentDao;
import com.payrollsys.dao.impl.DepartmentDaoImpl;
import com.payrollsys.entity.Department;
import com.payrollsys.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentDao deptDao = new DepartmentDaoImpl();
	
	@Override
	public int update(Department dept) {
		return this.deptDao.update(dept);
	}
	@Override
	public int add(Department dept) {
		return this.deptDao.save(dept);
	}

	@Override
	public List<Department> findAll() {		
		return this.deptDao.findAll();
	}

	@Override
	public int delete(int deptno) {
		
		return this.deptDao.delete(deptno);
	}

	@Override
	public Department findById(int deptno) {
		return this.deptDao.findById(deptno);
	}

}
