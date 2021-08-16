package com.payroll.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.payroll.entity.Department;
import com.payroll.service.DepartmentService;
import com.payroll.service.impl.DepartmentServiceImpl;
/**
 * @Test conduct the test
 * @Ignore ignore this test
 * 
 * Test the method's naming ： testMethod() is recommend but not necessary
 * @author Ingrid Liu & Ryan Mah
 *
 */
public class TestDepartService {
	@Test
	public void testDelete(){
		DepartmentService  deptService = new DepartmentServiceImpl();
		deptService.delete(4);
	}
	
	
	@Ignore
	@Test
	public void testAdd(){
		DepartmentService  deptService = new DepartmentServiceImpl();
		
		Department dept = new Department(1, "Boston Headquarter Office", "502");
		
		int n = deptService.add(dept);
		System.out.println(n);
	}
	
	@Test
	public void testFindAll(){
		DepartmentService  deptService = new DepartmentServiceImpl();
		List<Department> list = deptService.findAll();
		System.out.println(list.size());
		
	}
}
