package com.payrollsys.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/codeCheckServlet")
public class CodeCheckServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Set the request encoding format
            req.setCharacterEncoding("utf-8");
        //Set response encoding format
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
        //Get request information
            String userCode=req.getParameter("userCode");
        //Processing request
            //Get the verification code from the session
            String sysCode= (String) req.getSession().getAttribute("randStr");
        //response the result
            resp.getWriter().write(sysCode.equals(userCode)+"");
    }
}
