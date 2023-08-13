package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.BottleLetterDao;
import com.codingquokka.bottle.vo.BottleLetterVO;
import com.codingquokka.bottle.vo.UserVO;
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

    public List<BottleLetterVO> getReceivedBottles(String email) {
        return bottleLetterDao.getReceivedBottles(email);
    }

    public List<BottleLetterVO> getSentBottles(String email) {
        return bottleLetterDao.getSentBottles(email);
    }

    public BottleLetterVO getBottleDetail(Map<String, Object> param) {

        BottleLetterVO bottle = bottleLetterDao.getBottleDetail(param);
        if (bottle.getIsReportedMsg().equals("Y")) {
            BottleLetterVO reportedBottle = new BottleLetterVO() {{
                setIsReportedMsg("Y");
            }};
            return reportedBottle;
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
