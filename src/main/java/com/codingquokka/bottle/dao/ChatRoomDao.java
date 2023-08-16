package com.codingquokka.bottle.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.chatRoomMapper";

}
