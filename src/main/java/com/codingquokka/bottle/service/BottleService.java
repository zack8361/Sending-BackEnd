package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.BottleLetterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    public Map<String, Object> getBottleDetail(Map<String, Object> param) {

        Map<String, Object> bottle = bottleLetterDao.getBottleDetail(param);
        if (bottle.get("IS_REPORTED_MSG").equals("Y")) {
            Map<String, Object> result = new HashMap<>();
            bottle.clear();
            bottle.put("IS_REPORTED_MSG", "Y");

            return bottle;
        }
        else {
            bottleLetterDao.changeIsRead(param);
            return bottle;
        }
    }

    public int reportBottleLetter(Map<String, Object> param) {
        return bottleLetterDao.reportBottleLetter(param);
    }

    public int sendBottleLetter(Map<String, Object> param) {

        param.put("receiver_id", bottleLetterDao.getLeastReceivedUser((String) param.get("sender_id")));
        userService.upCntReceived(param.get("receiver_id").toString());

        return bottleLetterDao.sendBottleLetter(param);
    }

}
