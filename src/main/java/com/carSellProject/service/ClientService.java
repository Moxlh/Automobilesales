package com.carSellProject.service;

import com.carSellProject.mapper.ClientMapper;
import com.carSellProject.pojo.Client;
import com.carSellProject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
public class ClientService {
    //1. 获取factory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //2. 获取SqlSession
    SqlSession sqlSession = factory.openSession();
    //3. 获取ClientMapper
    ClientMapper mapper = sqlSession.getMapper(ClientMapper.class);

    public Client selectByName(String name){
        return mapper.selectByName(name);
    }

    public List<Client> getClientsByPId(int pId){
        return mapper.getClientsByPId(pId);
    }

    public Client getCByName(String name){
        return mapper.getCByName(name);
    }
}
