// src/main/java/com/sanda/sandaenvmonitor/service/UserServiceImpl.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // 注入 PasswordEncoder

    @Override
    public void registerUser(User user) throws Exception {
        // 检查用户名是否已经存在
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("用户名已存在！");
        }

        // 对密码进行加密
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 保存用户信息
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    @Override
    public User getCurrentUser() {
        // 从 Spring Security 中获取当前登录的用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // 从数据库中查找用户并返回
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}