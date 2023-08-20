package com.codingquokka.bottle.dao;


import com.codingquokka.bottle.vo.ChatRoomVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ChatRoomDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.chatRoomMapper";

    public int isChatRoomOpen(String chatId) {
        return sqlSession.selectOne(namepace + ".isChatRoomOpen", chatId);
    }

    public int openChatRoom(ChatRoomVO chatRoomVO) {
        return sqlSession.insert(namepace+".openChatRoom", chatRoomVO);
    }

    public String getUserId(Map<String, String> param) {
        return sqlSession.selectOne(namepace+".getUserId", param);
    }
}
