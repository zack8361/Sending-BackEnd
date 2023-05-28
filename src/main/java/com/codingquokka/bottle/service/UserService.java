package com.codingquokka.bottle.service;

import com.codingquokka.bottle.core.AES128;
import com.codingquokka.bottle.core.MessageUtils;
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
    private AES128 aes128;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailDomainService mailDomainService;

    public Map<String, Object> login(Map<String, Object> map){
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

//        String emailContent = MessageUtils.getMessage("send.cert.email").replace("${link}",MessageUtils.getMessage("server.ip")+"/certUser/"+ aes128.encrypt(map.get("uuid").toString()));
//        mailService.sendMail(map.get("email").toString(), "[Bottle] 인증을 완료해주세요", emailContent);

        return result;
    }
    public int checkEmail(Map<String, Object> map) { return userDao.checkEmail(map); }

    public void insertTest() {
        userDao.insertTest();
        throw new RuntimeException();
    }

    public int cert(String uuid) {
        return userDao.cert(uuid);
    }
}
