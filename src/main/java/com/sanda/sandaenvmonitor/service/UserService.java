// src/main/java/com/sanda/sandaenvmonitor/service/UserService.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;

public interface UserService {

    User findByUsername(String username);
    void registerUser(User user, String verificationCode) throws Exception;
    void sendVerificationCode(String email) throws Exception;

    User getCurrentUser();
}