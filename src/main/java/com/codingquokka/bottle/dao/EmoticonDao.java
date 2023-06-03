package com.codingquokka.bottle.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EmoticonDao {
    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.emoticonMapper";

    public String getEmoticon(int emoId) {
        return sqlSession.selectOne(namepace+".getEmoticon", emoId);
    }
}
