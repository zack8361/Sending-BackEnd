package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.FcmTokenDao;
import com.codingquokka.bottle.vo.FcmTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class FcmTokenService {

    @Autowired
    private FcmTokenDao fcmTokenDao;

    public void insertToken(HashMap<String, Object> map) {
        if (fcmTokenDao.isTokenExist(map) == 0) {
            fcmTokenDao.insertToken(map);
        }
    }

    public List<FcmTokenVO> getToken(HashMap<String, Object> param) {

        return fcmTokenDao.getToken(param);
    }
}
