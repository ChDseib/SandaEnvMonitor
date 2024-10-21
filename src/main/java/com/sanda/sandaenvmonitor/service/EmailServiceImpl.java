package com.sanda.sandaenvmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sanda.sandaenvmonitor.model.User;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(User user) {
        String to = user.getEmail();
        String subject = "Email Verification";
        String text = "Please click the link to verify your email: " +
                "http://localhost:8080/verify?token=" + user.getVerificationToken();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
