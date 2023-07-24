package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.FcmTokenDao;
import com.codingquokka.bottle.vo.FcmTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class FcmTokenService {

    @Autowired FcmTokenDao fcmTokenDao;
    public void insertToken(HashMap<String, Object> map) {
        fcmTokenDao.insertToken(map);
    }

    public FcmTokenVO getToken(HashMap<String, Object> param) {

        return fcmTokenDao.getToken(param);
    }
}
