package com.codingquokka.bottle.dao;

import com.codingquokka.bottle.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.userMapper";

    public Map<String, Object> login(Map<String, Object> map) {
        return sqlSession.selectOne(namepace+".login", map);
    }
    public int join(Map<String, Object> map) {return sqlSession.insert(namepace+".join", map); }
    public void insertTest() {
        sqlSession.insert(namepace+".insertTest");
    }
    public int checkEmail(Map<String,Object> map) {return sqlSession.selectOne(namepace+".checkEmail",map);}
    public int cert(String uuid) {
        return sqlSession.update(namepace+".cert", uuid);
    }
    public int checkUser(Map<String, Object> map) {
        return sqlSession.selectOne(namepace+".checkUser",map);
    }
}
