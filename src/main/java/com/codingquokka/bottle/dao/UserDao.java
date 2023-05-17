package com.codingquokka.bottle.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@Transactional
public class UserDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.userMapper";

    public Map<String, Object> login(Map<String, Object> map) {
        return sqlSession.selectOne(namepace+".login", map);
    }

    public void insertTest() {
        sqlSession.insert(namepace+".insertTest");
    }

    public int cert(String uuid) {
        return sqlSession.update(namepace+".cert", uuid);
    }
}
