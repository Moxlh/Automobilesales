package com.carSellProject.service;

import com.carSellProject.mapper.PeopleMapper;
import com.carSellProject.pojo.People;
import com.carSellProject.pojo.Sell_log;
import com.carSellProject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
public class EmployeeService {
    //1. 获取factory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //2. 获取SqlSession
    SqlSession sqlSession = factory.openSession();
    //3. 获取BookMapper
    PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);

    public People selectByName(String name){
        return mapper.selectByName(name);
    }


}
