package com.carSellProject.mapper;

import com.carSellProject.pojo.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleMapper {
    People selectByName(@Param("name") String name);

    List<People> selectAll();

    List<People> getPeopleByMId(@Param("mId") int mId);

    void updatePwdById(int id);

    void updatePNameById(int id);
}
