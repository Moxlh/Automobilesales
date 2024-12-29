package com.carSellProject.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        //判断访问资源路径是否与登录注册有关
        String[] urls = {"/login.jsp", "/loginServlet"};
        //获取当前资源访问路径
        String requestURI = req.getRequestURI();
        //循环判断
        for(String str : urls){
            if(requestURI.contains(str)){
                //放行
                chain.doFilter(request, response);
                return;
            }
        }

        //1. 判断session中是否有user
        Object user = req.getSession().getAttribute("user");

        //2. 判断user是否为null
        if(user != null){
            //登录过了
            //放行
            chain.doFilter(request, response);
        }
        else{
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }


    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
