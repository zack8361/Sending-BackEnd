package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.ChatRoomDao;
import com.codingquokka.bottle.vo.ChatRoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
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
        int i = 0;
        param.put("uuid", UUID.randomUUID());

        ChatRoomVO[] chatRoomVOs = new ChatRoomVO[2];
        chatRoomVOs[0] = new ChatRoomVO() {{
            setChatId(param.get("chatId").toString());
            setUserId(param.get("senderId").toString());
            setIsUsing(1);
        }};

        chatRoomVOs[1] = new ChatRoomVO() {{
            setChatId(param.get("chatId").toString());
            setUserId(param.get("receiverId").toString());
            setIsUsing(1);
        }};

        for(ChatRoomVO chatRoomVO : chatRoomVOs) {
            chatRoomDao.openChatRoom(chatRoomVO);
            i++;
        }

        if (i != 2) {
            throw new RuntimeException("정상적이지 않은 생성");
        }

        return i;
    }
}
