package com.codingquokka.bottle.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MailDomainDao {

    @Autowired
    SqlSession sqlSession;

    private static String namepace = "mapper.mailDomainMapper";

    public String checkMailDomain(String domain_cd) {
        return sqlSession.selectOne(namepace+".checkMailDomain", domain_cd);
    }

    public int addMailDomain(Map<String, Object> param) {
        return sqlSession.insert(namepace+".addMailDomain", param);
    }

}
