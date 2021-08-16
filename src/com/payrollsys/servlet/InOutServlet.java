package com.payrollsys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.payrollsys.service.InOutService;
import com.payrollsys.service.impl.InOutServiceImpl;


public class InOutServlet extends BaseServlet {

	public void getPieData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stype = request.getParameter("type");
		int type = 0;
		try{
			type = Integer.parseInt(stype);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//调用业务层获取jsonStr
		InOutService ioService = new InOutServiceImpl();
		String jsonStr = ioService.getPieData(type);//0代表查询所有时间段  1  2  3
		
		
		//返回JsonStr
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(jsonStr);
		
	}
	public void getBarData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用业务层获取jsonStr
		//String jsonStr = "[['Mon1', 'Tue2', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],[120, 200, 1500, 800, 70, 110, 130]]";
		InOutService ioService = new InOutServiceImpl();
		String jsonStr = ioService.getBarData();
		//返回JsonStr
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(jsonStr);
		
	}

}
