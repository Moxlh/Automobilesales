package com.carSellProject.web;

import com.carSellProject.mapper.WarehouseMapper;
import com.carSellProject.pojo.Client;
import com.carSellProject.pojo.Sell_log;
import com.carSellProject.pojo.Warehouse;
import com.carSellProject.service.Sell_logService;
import com.carSellProject.service.WarehouseService;
import com.carSellProject.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
@WebServlet("/clientServlet")
public class ClientServlet extends HttpServlet {
    WarehouseService warehouseService = new WarehouseService();
    Sell_logService sellLogService = new Sell_logService();

    // 处理GET请求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取method参数，判断是调用哪个方法
        String method = request.getParameter("method");

        // 如果method为getAllCars，则获取所有车辆
        if ("getAllCars".equals(method)) {
            String result = getAllCars(request, response);
            request.getRequestDispatcher(result).forward(request, response);
        }
        // 如果method为getMyOrders，则获取用户订单
        else if ("getMyOrders".equals(method)) {
            String result = getMyOrders(request, response);
            request.getRequestDispatcher(result).forward(request, response);
        }
        // 如果没有指定method参数，默认返回一个错误页面或首页
        else {
            response.sendRedirect("error.jsp");
        }
    }

    // 处理POST请求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST请求的处理，如果没有特别的POST处理逻辑，你可以做类似的参数处理
        String method = request.getParameter("method");

        // 目前没有指定特别的POST方法处理，根据需求你可以扩展
        if ("addOrder".equals(method)) {
            // 执行添加订单等POST操作
            // String result = addOrder(request, response); // 你可以定义一个方法
            // request.getRequestDispatcher(result).forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    public String getAllCars(HttpServletRequest request, HttpServletResponse response){
        List<Warehouse> cars = warehouseService.getCars();
        request.setAttribute("cars", cars);
        request.setAttribute("flag", 1);
        return Constants.FORWARD + "client.jsp";
    }

    public String getMyOrders(HttpServletRequest request, HttpServletResponse response){
        Client user = (Client) request.getSession().getAttribute("user");
        String name = user.getName();
        List<Sell_log> sellLogs = sellLogService.selectByCName(name);
        request.setAttribute("flag", 2);
        request.setAttribute("sellLogs", sellLogs);
        return Constants.FORWARD + "client.jsp";
    }

}
