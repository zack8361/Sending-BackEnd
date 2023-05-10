package com.codingquokka.bottle.dao;

import com.codingquokka.bottle.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@Transactional
public class UserDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.userMapper";

    public Map<String, Object> login() {
        return sqlSession.selectOne(namepace+".loginTest");
    }

    public void insertTest() {
        sqlSession.insert(namepace+".insertTest");
    }
}
