// src/main/java/com/sanda/sandaenvmonitor/service/UserServiceImpl.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.model.VerificationCode;
import com.sanda.sandaenvmonitor.repository.UserRepository;
import com.sanda.sandaenvmonitor.repository.VerificationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // 注入 PasswordEncoder

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;



    private static final int CODE_EXPIRY_MINUTES = 5;

    @Override
    public void sendVerificationCode(String email) throws Exception {
        // 检查用户是否已经注册
        if (userRepository.existsByEmail(email)) {
            throw new Exception("该邮箱已被注册。");
        }

        // 生成六位随机数
        String code = String.format("%06d", new Random().nextInt(999999));

        // 发送邮件
        emailService.sendVerificationCode(email, code);

        // 存储验证码
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
        if (verificationCode == null) {
            verificationCode = new VerificationCode();
            verificationCode.setEmail(email);
        }
        verificationCode.setCode(code);
        verificationCode.setExpiryTime(LocalDateTime.now().plusMinutes(CODE_EXPIRY_MINUTES));
        verificationCodeRepository.save(verificationCode);
    }

    @Override
    @Transactional
    public void registerUser(User user, String verificationCode) throws Exception {
        // 检查用户名和邮箱是否已存在
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("用户名已存在。");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("邮箱已被注册。");
        }

        // 验证验证码
        VerificationCode storedCode = verificationCodeRepository.findByEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // 对密码进行加密
        if (storedCode == null) {
            throw new Exception("验证码不存在，请先获取验证码。");
        }

        if (storedCode.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new Exception("验证码已过期，请重新获取。");
        }

        if (!storedCode.getCode().equals(verificationCode)) {
            throw new Exception("验证码不正确。");
        }

        // 保存用户
        userRepository.save(user);

        // 删除验证码
        verificationCodeRepository.deleteByEmail(user.getEmail());
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

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void saveVerificationToken(User user, String token) {

    }

    @Override
    public boolean verifyToken(String token) {
        return false;
    }
}