package com.payroll.test;

import java.util.List;

import org.junit.Test;

import com.payroll.entity.Employee;
import com.payroll.service.EmployeeService;
import com.payroll.service.impl.EmployeeServiceImpl;

public class TestEmployeeService {
	
	@Test
	public void testFindAll(){
		EmployeeService  empService = new EmployeeServiceImpl();
		List<Employee> empList = empService.findAll();
		System.out.println(empList);
	}
}
