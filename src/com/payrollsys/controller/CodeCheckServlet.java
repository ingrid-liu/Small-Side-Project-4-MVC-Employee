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
        //设置请求编码格式
            req.setCharacterEncoding("utf-8");
        //设置响应编码格式
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
        //获取请求信息
            String userCode=req.getParameter("userCode");
        //处理请求
            //获取session中存储系统验证码
            String sysCode= (String) req.getSession().getAttribute("randStr");
        //响应结果
            resp.getWriter().write(sysCode.equals(userCode)+"");
    }
}
