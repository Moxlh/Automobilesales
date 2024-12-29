package com.carSellProject.mapper;

import com.carSellProject.pojo.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientMapper {
    Client selectByName(String name);

    List<Client> getClientsByPId(@Param("pId") int pId);

    Client getCByName(@Param("name") String name);

    void updatePwdById(int id);

    void updateCNameById(int id);
}
