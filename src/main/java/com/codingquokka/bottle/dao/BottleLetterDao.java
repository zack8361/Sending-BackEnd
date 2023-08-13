package com.codingquokka.bottle.dao;

import com.codingquokka.bottle.vo.BottleLetterVO;
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

    public List<BottleLetterVO> getReceivedBottles(String email) {
        return sqlSession.selectList(namepace+".getReceivedBottles", email);
    }

    public List<BottleLetterVO> getSentBottles(String email) {
        return sqlSession.selectList(namepace+".getSentBottles", email);
    }

    public BottleLetterVO getBottleDetail(Map<String, Object> map) {
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
