// src/main/java/com/sanda/sandaenvmonitor/service/UserService.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;

public interface UserService {
    void registerUser(User user) throws Exception;
    User findByUsername(String username);


    User getCurrentUser();
}