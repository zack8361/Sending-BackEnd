package com.codingquokka.bottle.dao;

import com.codingquokka.bottle.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.loginMapper";

    public UserVO login(UserVO vo) throws Exception{
        return sqlSession.selectOne(namepace+".login",vo);
    }

}
