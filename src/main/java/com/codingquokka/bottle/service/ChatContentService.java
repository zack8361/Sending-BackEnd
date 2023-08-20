package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.ChatContentDao;
import com.codingquokka.bottle.vo.ChatContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatContentService {

    @Autowired
    private ChatContentDao chatContentDao;
    public List<ChatContentVO> getChatContentList(String chatId) {
        return chatContentDao.getChatContentList(chatId);
    }
}
