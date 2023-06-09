package com.codingquokka.bottle.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BottleLetterDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.bottleMapper";

    public List<Map<String, Object>> getReceivedBottles(Map<String, Object> map ) {
        return sqlSession.selectList(namepace+".getReceivedBottles", map);
    }

    public List<Map<String, Object>> getSentBottles(Map<String, Object> map ) {
        return sqlSession.selectList(namepace+".getSentBottles", map);
    }

    public Map<String, Object> getBottleDetail(Map<String, Object> map ) {
        return sqlSession.selectOne(namepace+".getBottleDetail", map);
    }

    public int sendBottleLetter(Map<String, Object> map) {
        return sqlSession.insert(namepace+".sendBottleLetter", map);
    }

    public int changeIsRead(Map<String, Object> map) {
        return sqlSession.update(namepace+".changeIsRead", map);
    }

    public int reportBottleLetter(Map<String, Object> map) {
        return sqlSession.update(namepace+".reportBottleLetter", map);
    }

    public String getLeastReceivedUser(String email) {
        return sqlSession.selectOne(namepace+".getLeastReceivedUser", email);
    }
}
