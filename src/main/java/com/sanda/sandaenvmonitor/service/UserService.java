// src/main/java/com/sanda/sandaenvmonitor/service/UserService.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;

public interface UserService {
    void registerUser(User user) throws Exception;
    User findByUsername(String username);


    User getCurrentUser();

    void saveUser(User user);

    void saveVerificationToken(User user, String token);

    boolean verifyToken(String token);
}