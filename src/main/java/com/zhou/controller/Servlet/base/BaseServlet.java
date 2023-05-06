package com.zhou.controller.Servlet.base;


import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
       System.out.println("service 已经在运行...");
       try {
           request.setCharacterEncoding("utf-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       response.setCharacterEncoding("utf-8");
       //获取请求路径
       String uri =request.getRequestURI();
       //获取最后一段路径，方法名
       int index = uri.lastIndexOf("/");
       String methodName = uri.substring(index+1);

       System.out.println("methodName = " + methodName);
        //获取字节码文件对象
       Class<? extends BaseServlet> actionClass = this.getClass();
       try{
           //获取方法Method对象
           Method method = actionClass.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);

           //执行方法
           method.invoke(this,request,response);
       } catch (Exception e) {
           e.getStackTrace();
       }
   }

   public void writeValue(Object object, HttpServletResponse response){
       response.setCharacterEncoding("GBK");
       String json = JSON.toJSONString(object);
       response.setContentType("application/json;charset=utf-8");
       try {
           PrintWriter out = response.getWriter();
           out.print(json);
           out.flush();
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public String writeValueAsString(Object object){
       return JSON.toJSONString(object);
   }


}
