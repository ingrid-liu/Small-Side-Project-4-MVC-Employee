package com.bjsxt.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Department;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Position;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.EmployeeServiceImpl;

public class EmployeeServlet extends BaseServlet {
	/**
	 * 注销操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//结束当前的session
		request.getSession().invalidate();
		
		//跳转到登录页面:注销之后建议使用重定向跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取登录信息
		String empId = request.getParameter("empId");
		String password = request.getParameter("password");
		
//		String verifyCode = request.getParameter("verifyCode");//用户输入的验证码
//		String randStr = (String)request.getSession().getAttribute("randStr");//正确的验证码
//		if(verifyCode ==null || !verifyCode.equals(randStr)){
//			request.setAttribute("error", "验证码错误");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
//			return;//return不可少  
//		}
		//调用业务层完成登录操作
		EmployeeService  empService = new EmployeeServiceImpl();
		Employee emp =empService.login(empId,password);
		
		//页面跳转
		if(emp != null){
			//将员工信息保存在session中
			request.getSession().setAttribute("emp", emp);//!!!!!
			response.sendRedirect(request.getContextPath()+"/main.html");
		}else{
			request.setAttribute("error", "用户名或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * 预更新操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//接收要修改的员工的编号
		String empId = request.getParameter("empId");
		
		//调用业务层获取该员工的信息
		EmployeeService empService = new EmployeeServiceImpl();
		Employee emp = empService.findById(empId);
		request.setAttribute("emp", emp);
		
		//获取所有的部门信息
		DepartmentService deptService = new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		//获取所有的岗位信息
		
		
		//获取上级员工		
		List<Employee> mgrList = empService.findEmpByType(2);//1  基层员工  2 各级管理人员
		request.setAttribute("mgrList",mgrList);
		
		//页面跳转 system/empUpdate.jsp
		request.getRequestDispatcher("/system/empUpdate.jsp").forward(request, response);
	}
	
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取员工的信息
		String empId = request.getParameter("empId");
		String password ="123456";
		String realName = request.getParameter("realName");
		String sex = request.getParameter("sex");
		//日期类型的处理
		String sbirthDate = request.getParameter("birthDate");
		String shireDate = request.getParameter("hireDate");
		String sleaveDate = request.getParameter("leaveDate");
		
		Date birthDate= null,hireDate = null,leaveDate = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthDate = sdf.parse(sbirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			hireDate = sdf.parse(shireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			leaveDate = sdf.parse(sleaveDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//整数的处理
		int onDuty = Integer.parseInt(request.getParameter("onDuty"));
		int empType = Integer.parseInt(request.getParameter("empType"));
		
		String phone = request.getParameter("phone");
		String qq = request.getParameter("qq");
		String emerContactPerson = request.getParameter("emerContactPerson");
		String idCard = request.getParameter("idCard");
		
		//对象的处理
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		Department dept = new Department();
		dept.setDeptno(deptno);
		
		int posId = Integer.parseInt(request.getParameter("posId"));
		Position position = new Position();
		position.setPosId(posId);
		String mgrId = request.getParameter("mgrId");
		Employee mgr = new Employee();
		mgr.setEmpId(mgrId);//!!!
		
		//调用业务层完成添加操作
		Employee emp = new Employee(empId, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
		EmployeeService  empService = new EmployeeServiceImpl();
		int n = empService.update(emp);
		
		//根据结果进行页面跳转
		if(n>0){
			response.sendRedirect(request.getContextPath()+"/servlet/EmployeeServlet?method=findEmp");
			//request.getRequestDispatcher("/servlet/EmployeeServlet?method=findEmp").forward(request, response);
		}else{
			request.setAttribute("error", "更新员工失败");
			request.getRequestDispatcher("/system/empUpdate.jsp").forward(request, response);
		}
		
	}
	/**
	 * 删除delete完毕后，跳转到findEmp，两个方法中都有String empId = request.getParameter("empId");
	 * 因为使用了转发，导致数据被意外的共享并接收，不是所希望的结果
	 * 解决办法
	 * 1.key不同empId2=liukaili
	 * 2.转发改成重定向
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收要删除的员工的编号
		String empId = request.getParameter("empId2");		
		//调用业务层完成删除操作
		EmployeeService  empService = new EmployeeServiceImpl();
		empService.delete(empId);
		
		//跳转到??? 不能直接跳转到页面，只是负责显示。要跳转到servlet-findEmp() ,先查询再显示
		request.getRequestDispatcher("/servlet/EmployeeServlet?method=findEmp").forward(request, response);
		//this.findAll(request, response);
	}
	
	public void findEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收查询条件
		String empId = request.getParameter("empId");
		
		int deptno = 0;
		String sdeptno = request.getParameter("deptno"); //null
		try{
			deptno = Integer.parseInt(sdeptno);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		int onDuty = 1;
		String sonDuty = request.getParameter("onDuty");//null		
		try{
			onDuty = Integer.parseInt(sonDuty);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		String shireDate = request.getParameter("hireDate");//null
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date hireDate = null;
		if(shireDate != null){
			try {
				hireDate = sdf.parse(shireDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		//调用业务层获取所有的员工信息
		EmployeeService  empService = new EmployeeServiceImpl();
		//List<Employee> empList = empService.findAll();
		//条件如果多，可以封装到一个对象中，使用一个对象做参数
		List<Employee> empList = empService.findEmp(empId,deptno,onDuty,hireDate);
		//获取所有部门的信息
		DepartmentService deptService = new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		
		//跳转到system/empList.jsp
		request.setAttribute("empId", empId);
		request.setAttribute("deptno", deptno);
		request.setAttribute("onDuty", onDuty);
		request.setAttribute("hireDate", shireDate);
		request.setAttribute("empList", empList);
		request.getRequestDispatcher("/system/empList.jsp").forward(request, response);
		
	}
	
	
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用业务层获取所有的员工信息
		EmployeeService  empService = new EmployeeServiceImpl();
		List<Employee> empList = empService.findAll();
		//获取所有部门的信息
		DepartmentService deptService = new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		
		//跳转到system/empList.jsp
		request.setAttribute("empList", empList);
		request.getRequestDispatcher("/system/empList.jsp").forward(request, response);
		
	}
	
	public void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取所有的部门信息
		DepartmentService deptService = new DepartmentServiceImpl();
		List<Department> deptList = deptService.findAll();
		request.setAttribute("deptList", deptList);
		//获取所有的岗位信息
		
		
		//获取上级员工
		EmployeeService empService = new EmployeeServiceImpl();
		List<Employee> mgrList = empService.findEmpByType(2);//1  基层员工  2 各级管理人员
		request.setAttribute("mgrList",mgrList);
		//跳转到system/empAdd.jsp
		request.getRequestDispatcher("/system/empAdd.jsp").forward(request, response);
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取员工的信息
		String empId = request.getParameter("empId");
		String password ="123456";
		String realName = request.getParameter("realName");
		String sex = request.getParameter("sex");
		//日期类型的处理
		String sbirthDate = request.getParameter("birthDate");
		String shireDate = request.getParameter("hireDate");
		String sleaveDate = request.getParameter("leaveDate");
		
		Date birthDate= null,hireDate = null,leaveDate = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthDate = sdf.parse(sbirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			hireDate = sdf.parse(shireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			leaveDate = sdf.parse(sleaveDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//整数的处理
		int onDuty = Integer.parseInt(request.getParameter("onDuty"));
		int empType = Integer.parseInt(request.getParameter("empType"));
		
		String phone = request.getParameter("phone");
		String qq = request.getParameter("qq");
		String emerContactPerson = request.getParameter("emerContactPerson");
		String idCard = request.getParameter("idCard");
		
		//对象的处理
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		Department dept = new Department();
		dept.setDeptno(deptno);
		
		int posId = Integer.parseInt(request.getParameter("posId"));
		Position position = new Position();
		position.setPosId(posId);
		String mgrId = request.getParameter("mgrId");
		Employee mgr = new Employee();
		mgr.setEmpId(mgrId);//!!!
		
		//调用业务层完成添加操作
		Employee emp = new Employee(empId, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
		EmployeeService  empService = new EmployeeServiceImpl();
		int n = empService.add(emp);
		
		//根据结果进行页面跳转
		if(n>0){
			response.sendRedirect(request.getContextPath()+"/servlet/EmployeeServlet?method=findEmp");
		}else{
			request.setAttribute("error", "添加员工失败");
			request.getRequestDispatcher("/system/empAdd.jsp").forward(request, response);
		}
		
	}

	

}
