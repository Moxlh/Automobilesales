package com.carSellProject.web;

import com.carSellProject.pojo.Client;
import com.carSellProject.pojo.People;
import com.carSellProject.service.ClientService;
import com.carSellProject.service.EmployeeService;
import com.carSellProject.util.Constants;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();
    private ClientService clientService = new ClientService();
    private Map<String, String> positionPageMap = new HashMap<>();

    public LoginServlet() {
        // 初始化权限和页面的映射
        positionPageMap.put("0", Constants.FORWARD + "employee.jsp");
        positionPageMap.put("1", Constants.FORWARD + "manager.jsp");
        positionPageMap.put("2", Constants.FORWARD + "superManager.jsp");
    }


    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uName = request.getParameter("uName");
        String uPwd = request.getParameter("uPwd");
        String type = request.getParameter("type");


        if ("employee".equals(type)) {
            People people = employeeService.selectByName(uName);
            String password = people.getPassword();
            if (password!= null && password.equals(uPwd)) {
                request.getSession().setAttribute("user", people);
                String position = people.getPosition();
                String forwardPage = positionPageMap.get(position);
                if (forwardPage == null) {
                    throw new Exception("权限错误！");
                }
                request.setAttribute("employee", people);
                return forwardPage;
            } else {
                request.setAttribute("warnMsg", "用户名或密码错误！");
                return Constants.FORWARD + "login.jsp";
            }
        } else {
            Client client = clientService.selectByName(uName);
            String password = client.getPassword();
            if (password!= null && password.equals(uPwd)) {
                request.getSession().setAttribute("user", client);
                request.setAttribute("client", client);
                return Constants.FORWARD + "client.jsp";
            } else {
                request.setAttribute("warnMsg", "用户名或密码错误！");
                return Constants.FORWARD + "login.jsp";
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String result = login(request, response);
            if (result.startsWith(Constants.FORWARD)) {
                String page = result.substring(Constants.FORWARD.length());
                request.getRequestDispatcher(page).forward(request, response);
            } else if (result.startsWith(Constants.REDIRECT)) {
                String url = result.substring(Constants.REDIRECT.length());
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            // 处理异常，可以将异常信息存储在请求中，转发到错误页面
            e.printStackTrace();  // 打印栈追踪信息，方便调试
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}