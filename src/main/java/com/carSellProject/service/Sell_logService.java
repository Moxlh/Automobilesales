package com.carSellProject.service;

import com.carSellProject.mapper.Sell_logMapper;
import com.carSellProject.pojo.Sell_log;
import com.carSellProject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
public class Sell_logService {
    //1. 获取factory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //2. 获取SqlSession
    SqlSession sqlSession = factory.openSession();
    //3. 获取ClientMapper
    Sell_logMapper mapper = sqlSession.getMapper(Sell_logMapper.class);

    public List<Sell_log> selectByCName(String name){
        return mapper.selectByCName(name);
    }

    public List<Sell_log> selectLogByPId(int pId){
        return mapper.selectLogByPId(pId);
    }

    public void addSell_log(Sell_log s){
        mapper.addSell_log(s.getClient_id(), s.getEmployee_id(), s.getBrand(), s.getModel(), s.getColor(), s.getNum(), s.getSell_date(), s.getMoney());
        sqlSession.commit();
    }

    public List<Sell_log> selectAll(){
        return mapper.selectAll();
    }

    public Sell_log getBySId(int sId){
        return mapper.selectBySId(sId);
    }

    public void updateStateById(int sId, String sState){
        mapper.updateStateById(sId, sState);
        sqlSession.commit();
    }
}
