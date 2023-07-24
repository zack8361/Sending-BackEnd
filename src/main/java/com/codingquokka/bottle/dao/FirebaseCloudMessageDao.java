package com.codingquokka.bottle.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FirebaseCloudMessageDao {
    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.fcmMapper";

    public void insertToken(HashMap<String, Object> map) {
        sqlSession.insert(namepace +".insertToken",map);
    }
}
