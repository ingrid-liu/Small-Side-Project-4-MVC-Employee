package src.com.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/my")
public class MyServlet extends BaseServlet {

    // statement request deals with the logic methods
    public void testA(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("MyServlet.testA");
    }
    public void testB(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("MyServlet.testB");
    }
    public void testC(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        System.out.println("MyServlet.testC");
    }
}
