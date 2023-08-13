package com.codingquokka.bottle.dao;

import com.codingquokka.bottle.vo.UserVO;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.userMapper";

    public UserVO login(Map<String, Object> map) {
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
    public int upCntReceived(String email) {
        return sqlSession.update(namepace+".upCntReceived", email);
    }
    public int changePassword(Map<String,Object> map) {
        return sqlSession.update(namepace+".changePassword", map);
    }
    public int checkUser(UserVO userVO) {
        return sqlSession.selectOne(namepace+".checkUser",userVO);
    }

    public UserVO getUserInfo(String email) {
        return sqlSession.selectOne(namepace + ".getUserInfo", email);
    }
}
