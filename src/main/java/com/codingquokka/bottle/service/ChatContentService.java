package com.codingquokka.bottle.service;

import com.codingquokka.bottle.dao.ChatContentDao;
import com.codingquokka.bottle.dao.ChatRoomDao;
import com.codingquokka.bottle.vo.ChatContentVO;
import com.codingquokka.bottle.vo.FcmTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(transactionManager = "bottleTransactionManager")
public class ChatContentService {

    @Autowired
    private ChatContentDao chatContentDao;

    @Autowired
    private ChatRoomDao chatRoomDao;

    @Autowired
    private FcmTokenService fcmTokenService;

    @Autowired
    private FirebaseCloudMessageService firebaseCloudMessageService;

    public List<ChatContentVO> getChatContentList(String chatId) {
        return chatContentDao.getChatContentList(chatId);
    }
    public int insertChat(Map<String, String> param) throws Exception {
        ChatContentVO chatContentVO = new ChatContentVO() {{
            setChatRoomUuid(param.get("chatRoomUuid"));
            setUserId(param.get("userId"));
            setContent(param.get("content"));
        }};

        HashMap<String, Object> fcmMap = new HashMap<>() {{
            put("sender_id", chatRoomDao.getUserId(param));
        }};
        List<FcmTokenVO> tokenList = fcmTokenService.getToken(fcmMap);
        for(FcmTokenVO fcmTokenVO : tokenList) {
            firebaseCloudMessageService.sendMessageTo(
                    fcmTokenVO.getToken(),
                    "BottleLetter 알림",
                    "새로운 채팅 메세지가 도착했습니다");
        }

        return chatContentDao.insertChatContent(chatContentVO);
    }
}
