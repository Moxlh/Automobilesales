package com.carSellProject.web;

import com.carSellProject.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author CloudyW
 * @version 1.0
 */
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取方法参数
        String methodStr = request.getParameter("method");

        Class<? extends BaseServlet> aClass = this.getClass();

        try {
            //反射调用业务逻辑

            Method method = aClass.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            //执行
            Object invoke = method.invoke(this, request, response);

            //返回值判断类型
            //转发: forward<:> + 路径; 重定向: redirect<:>; 字符串: 无;
            if (invoke != null) {
                String path = (String) invoke;
                if(path.startsWith(Constants.FORWARD)) {
                    String paths[] = path.split(Constants.FLAG);
                    request.getRequestDispatcher(paths[1]).forward(request, response);
                }
                else if(path.startsWith(Constants.REDIRECT)){
                    String paths[] = path.split(Constants.FLAG);
                    response.sendRedirect(paths[1]);
                }
                else{
                    response.getWriter().println(path);
                }
            }
        } catch (Exception e) {
            System.out.println("method:" + methodStr + "; 传入的方法参数有误!" + e);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
