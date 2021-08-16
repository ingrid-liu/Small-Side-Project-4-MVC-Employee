package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.DepartmentDao;
import com.bjsxt.dao.impl.DepartmentDaoImpl;
import com.bjsxt.entity.Department;
import com.bjsxt.service.DepartmentService;

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
