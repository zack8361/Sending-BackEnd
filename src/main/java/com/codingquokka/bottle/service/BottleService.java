package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.BottleLetterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
public class BottleService {

    @Autowired
    private BottleLetterDao bottleLetterDao;

    @Autowired
    private UserService userService;

    public List<Map<String, Object>> getReceivedBottles(Map<String, Object> param) {
        return bottleLetterDao.getReceivedBottles(param);
    }

    public List<Map<String, Object>> getSentBottles(Map<String, Object> param) {
        return bottleLetterDao.getSentBottles(param);
    }

    public int sendBottleLetter(Map<String, Object> param) {

        param.put("receiver_id", bottleLetterDao.getLeastReceivedUser((String) param.get("sender_id")));
        userService.upCntReceived(param.get("receiver_id").toString());

        return bottleLetterDao.sendBottleLetter(param);
    }

}
