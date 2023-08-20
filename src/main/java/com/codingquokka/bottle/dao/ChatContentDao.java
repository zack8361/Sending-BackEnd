package com.codingquokka.bottle.dao;


import com.codingquokka.bottle.vo.ChatContentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatContentDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.chatContentMapper";

    public List<ChatContentVO> getChatContentList(String chatId) {
        return sqlSession.selectList(namepace + ".getChatContentList", chatId);
    }
}
