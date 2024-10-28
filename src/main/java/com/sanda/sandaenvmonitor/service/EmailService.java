package com.sanda.sandaenvmonitor.service;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    public void sendTestEmail() {
//        String testEmail = "458108197@qq.com"; // 你的邮箱
//        String verificationCode = generateVerificationCode(); // 生成一个验证码
//        sendVerificationEmail(testEmail, verificationCode); // 调用发送邮件的方法
//    }
//
//    private String generateVerificationCode() {
//        return String.valueOf((int) (Math.random() * 1000000));
//    }
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendVerificationEmail(String email, String verificationCode) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(email);
//            message.setSubject("验证码");
//            message.setText("您的验证码是: " + verificationCode);
//            mailSender.send(message);
//            System.out.println("验证码已发送到: " + email);
//        } catch (Exception e) {
//            e.printStackTrace(); // 记录异常
//        }
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("458108197@qq.com");
        message.setTo(toEmail);
        message.setSubject("验证码");
        message.setText("您的验证码是: " + code + "，请在注册时输入。");

        try {
            mailSender.send(message);
            logger.info("验证码已发送到: {}", toEmail);
        } catch (Exception e) {
            logger.error("发送验证码失败: {}", e.getMessage());
            throw new RuntimeException("发送验证码失败，请稍后重试。");
        }
    }
}