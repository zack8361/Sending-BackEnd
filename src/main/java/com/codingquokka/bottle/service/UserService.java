package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
public class UserService {
    @Autowired
    private UserDao userDao;

    public void insertTest() {
        userDao.insertTest();
        throw new RuntimeException();
    }

}
