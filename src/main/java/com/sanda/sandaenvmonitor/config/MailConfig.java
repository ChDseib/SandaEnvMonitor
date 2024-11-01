package com.sanda.sandaenvmonitor.config;

// src/main/java/com/yourpackage/config/MailConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // 配置从application.properties中获取
        mailSender.setHost("smtp.163.com");  // 163邮箱的SMTP服务器
        mailSender.setPort(465);  // 使用465端口

        mailSender.setUsername("13386278731@163.com");  // 替换为您的163邮箱地址
        mailSender.setPassword("ALiryXY59MAWFYgG");  // 替换为您的163邮箱密码或授权码

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");  // 启用SSL
        props.put("mail.smtp.ssl.trust", "smtp.163.com");  // 信任的SMTP主机
        props.put("mail.debug", "false");  // 可以根据需要设置为true以启用调试信息

        return mailSender;
    }
}
