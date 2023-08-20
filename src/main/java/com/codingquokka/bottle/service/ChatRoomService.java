package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.ChatRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomDao chatRoomDao;

    public boolean isChatRoomOpen(String chatId) {
        if (chatRoomDao.isChatRoomOpen(chatId) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int openChatRoom(HashMap<String, Object> param) {

        param.put("uuid", UUID.randomUUID());


        return chatRoomDao.openChatRoom(param);
    }
}
