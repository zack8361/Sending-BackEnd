package com.codingquokka.bottle.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void sendMail(String to, String subject, String text) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();
        message.setText(text,"UTF-8","html");
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setFrom("bottle@gmail.com");
        message.setSubject(subject);


//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, false, "UTF-8");
//        mimeMessageHelper.setFrom("bottle@gmail.com");
//        mimeMessageHelper.setTo(to);
//        mimeMessageHelper.setSubject(subject);
////        mimeMessageHelper.setText(text,true);

        emailSender.send(message);
    }
}
