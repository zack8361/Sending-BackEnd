package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.MailDomainDao;
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

    @Autowired
    private MailDomainDao mailDomainDao;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailDomainService mailDomainService;

    public UserVO login(Map<String, Object> map){
        return userDao.login(map);
    }

    public int join(Map<String, Object> map) throws Exception {
        int result = 0;

        String domCheck = mailDomainService.checkMailDomain(map.get("domain_cd").toString());

        if(domCheck == null) {
            if (mailDomainService.addMailDomain(map) > 0) {
                result = userDao.join(map);
            } else {
                throw new Exception();
            }
        } else if (domCheck.equals("0") || domCheck.equals("1")){
            result =  userDao.join(map);
        } else if (domCheck.equals("-1")){
            return -1;
        }
        mailService.sendMail(map.get("email").toString(), "[BottleProject] 인증을 완료해주세요", "cert_Mail","");
        return result;
    }
    public int checkEmail(Map<String, Object> map) {

        int count = userDao.checkEmail(map);

        if(count == 0) {
            String domCheck = mailDomainService.checkMailDomain(map.get("email").toString().split("@")[1]);

            if (domCheck.equals("-1")) {
                return -1;
            }else {
                return 0;
            }
        }
        else {
            return 1;
        }
    }

    public void insertTest() {
        userDao.insertTest();
        throw new RuntimeException();
    }

    public int cert(String uuid) {
        return userDao.cert(uuid);
    }

    public int upCntReceived(String email) {
        return userDao.upCntReceived(email);
    }
    public int changePassword(Map<String, Object> map) {
        return userDao.changePassword(map);
    }


    public UserVO getUserInfo(String email) {
        return userDao.getUserInfo(email);
    }
}
