package com.carSellProject.mapper;

import com.carSellProject.pojo.Sell_log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface Sell_logMapper {
    List<Sell_log> selectByCName(String name);

    List<Sell_log> selectLogByPId(@Param("pId") int pId);

    void addSell_log(
            @Param("client_id") int client_id,
            @Param("employee_id") int employee_id,
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("color") String color,
            @Param("num") int num,
            @Param("sell_date") Date sell_date,
            @Param("money") int money
    );

    List<Sell_log> selectAll();

    Sell_log selectBySId(@Param("sId") int sId);

    void updateStateById(@Param("sId") int sId, @Param("sState") String sState);
}
