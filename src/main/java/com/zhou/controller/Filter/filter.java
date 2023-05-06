package com.zhou.controller.Filter;

import com.zhou.po.MainUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/")
public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //放行前，对request数据进行处理
        HttpSession session = request.getSession();
        MainUser user = (MainUser) session.getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/login2.html").forward(request, servletResponse);
        }else {
            filterChain.doFilter(request, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
