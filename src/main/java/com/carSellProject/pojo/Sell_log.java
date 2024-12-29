package com.carSellProject.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author CloudyW
 * @version 1.0
 */
@Data
public class Sell_log {
    int id;
    String c_name;
    int client_id;
    String p_name;
    int employee_id;
    String brand;
    String model;
    String color;
    int num;
    Date sell_date;
    int money;
    String state;
}
