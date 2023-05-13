package com.codingquokka.bottle.service;

import com.codingquokka.bottle.vo.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

//    @Autowired
//    JavaMailSender javaMailSender;
//
//    public void sendMail(EmailMessage emailMessage) throws Exception {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//
//        mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
//        mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
//        mimeMessageHelper.setText(emailMessage.getMessage(), false); // 메일 본문 내용, HTML 여부
//        javaMailSender.send(mimeMessage);
//    }
}