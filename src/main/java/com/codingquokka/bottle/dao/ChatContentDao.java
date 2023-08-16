package com.codingquokka.bottle.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatContentDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.chatContentMapper";

}
