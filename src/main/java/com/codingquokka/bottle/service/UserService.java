package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.UserDao;
import com.codingquokka.bottle.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
public class UserService {
    @Autowired
    private UserDao userDao;

    public Map<String, Object> login(Map<String, Object> map){
        return userDao.login(map);
    }
    public void insertTest() {
        userDao.insertTest();
        throw new RuntimeException();
    }

}
