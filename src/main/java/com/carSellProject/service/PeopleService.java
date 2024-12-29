package com.carSellProject.service;

import com.carSellProject.mapper.PeopleMapper;
import com.carSellProject.pojo.People;
import com.carSellProject.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author CloudyW
 * @version 1.0
 */
public class PeopleService {
    //1. 获取factory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //2. 获取SqlSession
    SqlSession sqlSession = factory.openSession();
    //3. 获取PeopleMapper
    PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);

    public List<People> getAllPeople(){
        return mapper.selectAll();
    }

    public List<People> getPeopleByMId(int mId){
        return mapper.getPeopleByMId(mId);
    }
}
