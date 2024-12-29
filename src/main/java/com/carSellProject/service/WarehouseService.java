package com.carSellProject.service;

import com.carSellProject.mapper.WarehouseMapper;
import com.carSellProject.pojo.Warehouse;
import com.carSellProject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
public class WarehouseService {
    //1. 获取factory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //2. 获取SqlSession
    SqlSession sqlSession = factory.openSession();
    //3. 获取WarehouseMapper
    WarehouseMapper mapper = sqlSession.getMapper(WarehouseMapper.class);

    public List<Warehouse> getCars(){
        return mapper.selectAll();
    }
}
