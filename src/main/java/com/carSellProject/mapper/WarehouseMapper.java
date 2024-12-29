package com.carSellProject.mapper;

import com.carSellProject.pojo.Warehouse;

import java.util.List;

public interface WarehouseMapper {
    List<Warehouse> selectAll();

    void addCars(Warehouse w);
}
