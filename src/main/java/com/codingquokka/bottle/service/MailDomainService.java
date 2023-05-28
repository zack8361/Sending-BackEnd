package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.MailDomainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
public class MailDomainService {
    @Autowired
    private MailDomainDao mailDomainDao;

    public String checkMailDomain(String domain_cd) {
        return mailDomainDao.checkMailDomain(domain_cd);
    }

    public int addMailDomain(Map<String, Object> param) {
        return mailDomainDao.addMailDomain(param);
    }

}
