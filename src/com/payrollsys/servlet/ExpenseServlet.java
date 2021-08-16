package com.payrollsys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.payrollsys.entity.Auditing;
import com.payrollsys.entity.Employee;
import com.payrollsys.entity.Expense;
import com.payrollsys.entity.ExpenseItem;
import com.payrollsys.service.ExpenseService;
import com.payrollsys.service.impl.ExpenseServiceImpl;
import com.payrollsys.util.MyException;


public class ExpenseServlet extends BaseServlet {
	
	/**
	 * 审核报销单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void audit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取审核表单的值
		//expId:expId,result:result,auditDesc:auditDesc
		int expId = Integer.parseInt(request.getParameter("expId"));
		String result = request.getParameter("result");
		String auditDesc = request.getParameter("auditDesc");
		Employee auditor = (Employee)request.getSession().getAttribute("emp");
		Date auditTime = new Date();
		//调用业务层完成审核操作、
		Auditing auditing = new Auditing(expId, result, auditDesc, auditor, auditTime);
		ExpenseService  expService = new ExpenseServiceImpl();
		
		try{			
			expService.audit(auditing);
			//成功
			response.getWriter().print("success");
			
		}catch(MyException e){
			e.printStackTrace();
			//失败
			response.getWriter().print("error");			
		}
		
		
		//输出结果ajax
		
	}
	/**
	 * 待审报销
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAudit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session中获取当前用户的编号
		Employee emp = (Employee)request.getSession().getAttribute("emp");
		String auditorId = emp.getEmpId();
		//调用业务层，查询到 当前用户需要审核的报销单列表
		ExpenseService  expService = new ExpenseServiceImpl();
		List<Expense> expList = expService.getToAudit(auditorId);//参数是当前用户的编号
		
		//跳转到指定页面 expense/myAudit.jsp
		request.setAttribute("expList", expList);
		request.getRequestDispatcher("/expense/toAudit.jsp").forward(request, response);
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收表单的数据
		String typeArr [] = request.getParameterValues("type");
		String amountArr [] = request.getParameterValues("amount");
		String itemDescArr [] = request.getParameterValues("itemDesc");
		
		List<ExpenseItem> itemList = new ArrayList<ExpenseItem>();
		double totalAmount = 0;
		for(int i=0;i<typeArr.length;i++){
			String type = typeArr[i];
			double amount = Double.parseDouble(amountArr[i]);
			String itemDesc = itemDescArr[i];
			ExpenseItem item = new ExpenseItem(type, amount, itemDesc);
			itemList.add(item);
			totalAmount += amount;
		}
		
		String empId = request.getParameter("empId");
		Date expTime = new Date();
		String expDesc = request.getParameter("expDesc");
		String nextAuditorId = request.getParameter("nextAuditorId");
		String lastResult ="新创建的";
		String status = "0";//新创建
		Expense expense = new Expense(empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status);
		expense.setItemList(itemList);
		//调用业务层完成添加操作
		ExpenseService expService  = new ExpenseServiceImpl();
		try{
			expService.add(expense);
			//成功页面跳转
			response.sendRedirect(request.getContextPath()+"/myExpense.html"); //???
		}catch(Exception e){
			e.printStackTrace();
			//失败 页面跳转
			request.setAttribute("error", "添加报销单失败");
			request.getRequestDispatcher("/expense/expenseAdd.jsp").forward(request, response);
			
		}
		
		//页面跳转
	}

}
