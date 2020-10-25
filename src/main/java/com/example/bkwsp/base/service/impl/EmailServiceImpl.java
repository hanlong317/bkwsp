package com.example.bkwsp.base.service.impl;

import com.example.bkwsp.base.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Resource
    @Value("spring.mail")
    private JavaMailSender mailSender;

    @Resource
    private Configuration freemarkerConfiguration;

    private String From = "1306507227@qq.com";

    public void send(String freemarkFile,Map<String,Object> map,String email){
        try{
            MimeMessage message =mailSender.createMimeMessage();
            MimeMessageHelper helper =new MimeMessageHelper(message,"UTF-8");
            helper.setFrom(From);
            helper.setTo(email);

            String test = getEmailTemplate(freemarkFile,map);
            String[] arr =test.split("&&&&");
            helper.setSubject(arr[0]);
            helper.setText(arr[1],true);
            mailSender.send(message);
            logger.info(email+"邮件发送成功！");
            System.out.println("发送成功");
        }catch(Exception e){
            System.out.println(e.getMessage());
            logger.error("邮件发送失败");
        }

    }

    private String getEmailTemplate(String templateFile, Map mapData) throws Exception{
        Template template = freemarkerConfiguration.getTemplate(templateFile);
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template,mapData);
        return htmlText;
    }
}
