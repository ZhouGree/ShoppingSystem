package com.zhou.controller.Servlet.demo;

import com.zhou.controller.Servlet.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/demo/*")
public class demoServlet extends BaseServlet {

    public void testDemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<h1>Hello, World! <h1>");
        printWriter.flush();
    }
}
