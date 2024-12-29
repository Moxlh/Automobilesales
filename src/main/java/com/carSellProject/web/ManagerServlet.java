package com.carSellProject.web;

import com.carSellProject.pojo.Client;
import com.carSellProject.pojo.People;
import com.carSellProject.pojo.Sell_log;
import com.carSellProject.pojo.Warehouse;
import com.carSellProject.service.ClientService;
import com.carSellProject.service.PeopleService;
import com.carSellProject.service.Sell_logService;
import com.carSellProject.service.WarehouseService;
import com.carSellProject.util.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
@WebServlet("/managerServlet")
public class ManagerServlet extends BaseServlet{
    WarehouseService warehouseService = new WarehouseService();
    Sell_logService sellLogService = new Sell_logService();
    ClientService clientService = new ClientService();
    PeopleService peopleService = new PeopleService();

    public String getAllCars(HttpServletRequest request, HttpServletResponse response){
        List<Warehouse> cars = warehouseService.getCars();
        request.setAttribute("cars", cars);
        request.setAttribute("flag", 1);
        return Constants.FORWARD + "manager.jsp";
    }

    public String getMyLogs(HttpServletRequest request, HttpServletResponse response){
        Client user = (Client) request.getSession().getAttribute("user");
        int pid = user.getId();
        List<Sell_log> sellLogs = sellLogService.selectLogByPId(pid);
        request.setAttribute("flag", 2);
        request.setAttribute("sellLogs", sellLogs);
        return Constants.FORWARD + "manager.jsp";
    }

    public String getMyClients(HttpServletRequest request, HttpServletResponse response){
        Client user = (Client) request.getSession().getAttribute("user");
        int pid = user.getId();
        List<Client> clients = clientService.getClientsByPId(pid);

        request.setAttribute("flag", 3);
        request.setAttribute("clients", clients);

        return Constants.FORWARD + "manager.jsp";
    }

    public String getMyPeople(HttpServletRequest request, HttpServletResponse response){
        Client user = (Client) request.getSession().getAttribute("user");
        int mid = user.getId();
        List<People> people = peopleService.getPeopleByMId(mid);

        request.setAttribute("flag", 4);
        request.setAttribute("people", people);

        return Constants.FORWARD + "manager.jsp";
    }
}
