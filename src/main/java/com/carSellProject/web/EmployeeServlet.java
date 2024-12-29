package com.carSellProject.web;

import com.carSellProject.pojo.Client;
import com.carSellProject.pojo.People;
import com.carSellProject.pojo.Sell_log;
import com.carSellProject.pojo.Warehouse;
import com.carSellProject.service.ClientService;
import com.carSellProject.service.Sell_logService;
import com.carSellProject.service.WarehouseService;
import com.carSellProject.util.Constants;
import org.apache.ibatis.annotations.Param;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
@WebServlet("/employeeServlet")
public class EmployeeServlet extends BaseServlet{
    WarehouseService warehouseService = new WarehouseService();
    Sell_logService sellLogService = new Sell_logService();
    ClientService clientService = new ClientService();

    public String getAllCars(HttpServletRequest request, HttpServletResponse response){
        List<Warehouse> cars = warehouseService.getCars();
        request.setAttribute("cars", cars);
        request.setAttribute("flag", 1);
        return Constants.FORWARD + "employee.jsp";
    }

    public String getMyLogs(HttpServletRequest request, HttpServletResponse response){
        People user = (People) request.getSession().getAttribute("user");
        int pid = user.getId();
        List<Sell_log> sellLogs = sellLogService.selectLogByPId(pid);
        request.setAttribute("flag", 2);
        request.setAttribute("sellLogs", sellLogs);
        return Constants.FORWARD + "employee.jsp";
    }

    public String getMyClients(HttpServletRequest request, HttpServletResponse response){
        People user = (People) request.getSession().getAttribute("user");
        int pid = user.getId();
        List<Client> clients = clientService.getClientsByPId(pid);
        request.setAttribute("flag", 3);
        request.setAttribute("clients", clients);
        return Constants.FORWARD + "employee.jsp";
    }
}
