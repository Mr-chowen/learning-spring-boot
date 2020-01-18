package com.xust.springboot;

import com.xust.springboot.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail()throws Exception{
        mailService.sendSimpleMail("1293162305@qq.com","happy new year！","祝你新年快乐，身体健康！");
    }

    @Test
    public void testHtmlMail()throws Exception{
        String content="<html>\n"+
                "<body>\n"+
                    "<h3>happy new year ！</h3>\n"+
                "</body>\n"+
                "</html>";
        mailService.sendHtmlMail("1293162305@qq.com","祝你新年快乐！",content);

    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="e:\\Music\\Angel - Westlife.mp3";
        mailService.sendAttachmentsMail("1293162305@qq.com", "happy new year ！", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "15-511-01";
        String content="<html><body>hello my friend：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\summer\\Pictures\\11.jpg";

        mailService.sendInlineResourceMail("1293162305@qq.com", "happy new year !", content, imgPath, rscId);
    }


}
