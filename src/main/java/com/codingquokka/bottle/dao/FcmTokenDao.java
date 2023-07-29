package com.codingquokka.bottle.dao;


import com.codingquokka.bottle.vo.FcmTokenVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class FcmTokenDao {
    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.fcmMapper";

    public void insertToken(HashMap<String, Object> map) {
        sqlSession.insert(namepace +".insertToken",map);
    }

    public int isTokenExist(HashMap<String, Object> param) {
        return sqlSession.selectOne(namepace +".isTokenExist",param);
    }

    public FcmTokenVO getToken(HashMap<String, Object> param) {
        return sqlSession.selectOne(namepace +".getToken",param);
    }
}
