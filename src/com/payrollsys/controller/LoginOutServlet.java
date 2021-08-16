package com.payrollsys.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/loginOutServlet")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //invalidate the session
        req.getSession().invalidate();
        //redirect
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
}
