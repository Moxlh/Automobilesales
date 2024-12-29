package com.carSellProject.web;

import com.carSellProject.pojo.People;
import com.carSellProject.pojo.Sell_log;
import com.carSellProject.service.ClientService;
import com.carSellProject.service.Sell_logService;
import com.carSellProject.util.Constants;
import com.mysql.cj.PreparedQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
@WebServlet("/sell_logServlet")
public class Sell_logServlet extends BaseServlet{
    Sell_logService sellLogService = new Sell_logService();
    ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 获取method参数，用于判断调用哪个方法
        String method = request.getParameter("method");

        try {
            // 判断method参数，并根据其值调用不同的方法
            if ("addSell_log".equals(method)) {
                // 处理添加销售记录
                String result = addSell_log(request, response);
                // 根据result返回页面
                request.getRequestDispatcher(result).forward(request, response);
            } else if ("selectAll".equals(method)) {
                // 处理查询所有销售记录
                String result = selectAll(request, response);
                request.getRequestDispatcher(result).forward(request, response);
            } else if ("solveSellLog".equals(method)) {
                // 处理解决销售记录
                String result = solveSellLog(request, response);
                request.getRequestDispatcher(result).forward(request, response);
            } else {
                // 如果method参数无效，则返回默认页面或错误页面
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            // 捕获异常并打印日志
            e.printStackTrace();
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public String addSell_log(HttpServletRequest request, HttpServletResponse response){
        System.out.println(0);
        People user = (People) request.getSession().getAttribute("user");
        String cName = request.getParameter("cName");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        int num = Integer.parseInt(request.getParameter("num"));
        int money = Integer.parseInt(request.getParameter("money"));

        String type = "";
        String position = user.getPosition();
        if (position.equals("2"))
            type = "superManager";
        else if (position.equals("1"))
            type = "manager";
        else
            type = "employee";

        int id = clientService.getCByName(cName).getId();
        Sell_log sellLog = new Sell_log();
        sellLog.setClient_id(id);
        sellLog.setEmployee_id(user.getId());
        sellLog.setBrand(brand);
        sellLog.setModel(model);
        sellLog.setColor(color);
        sellLog.setSell_date(new Date());
        sellLog.setNum(num);
        sellLog.setMoney(money);
        sellLog.setState("0");
        try {
            sellLogService.addSell_log(sellLog);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(4);
        return Constants.FORWARD + type + ".jsp";
    }

    public String selectAll(HttpServletRequest request, HttpServletResponse response){
        List<Sell_log> sellLogs = null;
        try {
            sellLogs = sellLogService.selectAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        request.setAttribute("flag", 6);
        request.setAttribute("sellLogs", sellLogs);

        return Constants.FORWARD + "superManager.jsp";
    }

    public String solveSellLog(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String result = request.getParameter("result");

        Sell_log sellLog = sellLogService.getBySId(id);
        int sId = sellLog.getId();
        sellLogService.updateStateById(sId, result);

        return Constants.FORWARD + "superManager.jsp";
    }
}
