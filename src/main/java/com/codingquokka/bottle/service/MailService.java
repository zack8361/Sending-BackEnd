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
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Async
    public void sendMail(String to, String subject, String templateName, String text) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setFrom("bottle@gmail.com");
        message.setSubject(subject);

        // 메일 내용 설정 : 템플릿 프로세스
        Context context = new Context();
        context.setVariable("to", to);
        String html = templateEngine.process(templateName,context);
        message.setText(html,"UTF-8","html");

        emailSender.send(message);
    }
}
