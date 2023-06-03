package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.EmoticonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
public class EmoticonService {
    @Autowired
    private EmoticonDao emoticonDao;

    public String getEmoticon(int emoId) {
        return emoticonDao.getEmoticon(emoId);
    }

}
