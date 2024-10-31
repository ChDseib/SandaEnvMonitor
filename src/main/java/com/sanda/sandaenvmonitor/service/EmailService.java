package com.sanda.sandaenvmonitor.service;

public interface EmailService {
    void sendVerificationCode(String toEmail, String code);
}