package com.payrollsys.controller;

import com.payrollsys.pojo.Employee;
import com.payrollsys.service.UserService;
import com.payrollsys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Set request encoding format
        req.setCharacterEncoding("utf-8");
        //Set response encoding format
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //Get request information
        String empid = req.getParameter("empid");
        String password = req.getParameter("password");
        //Processing request
            //Create business layer objects
            UserService userService = new UserServiceImpl();
            //Invoke business layer methods
            Employee employee = userService.empLoginService(empid, password);
        //responding result
        if(employee != null){
            //store the user info to session
            req.getSession().setAttribute("emp", employee);
            //redirect to the main.jsp
            //todo main.jsp
            resp.sendRedirect(req.getContextPath()+"/main.jsp");
        }else{
            //Store the prompt in the request scope
            req.setAttribute("flag","UserID or Password is incorrect");
            //forward the request? (prepend the contextpath of the respective application)
            req.getRequestDispatcher("/login.jsp").forward(req, resp);      //todo here should be login.jsp, right? 老师的是/jsp也work
        }
    }
}
