package com.codingquokka.bottle.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ChatRoomDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.chatRoomMapper";

    public int isChatRoomOpen(String chatId) {
        return sqlSession.selectOne(namepace + ".isChatRoomOpen", chatId);
    }

    public int openChatRoom(HashMap<String, Object> param) {
        return sqlSession.insert(namepace+".openChatRoom", param);
    }
}
