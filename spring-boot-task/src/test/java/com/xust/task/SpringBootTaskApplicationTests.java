package com.xust.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringBootTaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知");
        message.setText("今晚8：00在大会议室开会！");
        message.setTo("363582476@qq.com");
        message.setFrom("2362457887@qq.com");
        mailSender.send(message);
    }
    @Test
    void testMineMail() throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("通知");
        helper.setText("<b style='color:red'>今晚7：00在会议室开会！</b>",true);
        helper.addAttachment("IMG_0862.JPG",new File("C:\\Users\\dell\\Pictures\\2019-04\\IMG_0862.JPG"));
        helper.setTo("376042636@qq.com");
        helper.setFrom("2362457887@qq.com");

        mailSender.send(mimeMessage);
    }

}
