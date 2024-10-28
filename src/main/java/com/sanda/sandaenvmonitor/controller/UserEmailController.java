package com.sanda.sandaenvmonitor.controller;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import java.util.UUID;
//
//
//// 确保这些服务类存在于你的项目中
//import com.sanda.sandaenvmonitor.service.EmailService;
//import com.sanda.sandaenvmonitor.service.UserService;
//import com.sanda.sandaenvmonitor.model.User; // 假设 User 类在 model 包下
//@RestController
//@RequestMapping("/api/users")
//public class UserEmailController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private UserService userService; // 假设你有一个用户服务类处理用户逻辑
//
//    // 用户注册
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        // 保存用户到数据库（可以先保存未激活状态）
//        userService.saveUser(user);
//
//        // 生成验证 token
//        String token = UUID.randomUUID().toString();
//
//        // 将 token 保存到数据库（假设你有一个 TokenService）
//        userService.saveVerificationToken(user, token);
//
//        // 发送验证邮件
//        emailService.sendVerificationEmail(user.getEmail(), token);
//
//        return ResponseEntity.ok("Registration successful. Please check your email to verify.");
//    }
//
//    // 邮件验证
//    @GetMapping("/verify")
//    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
//        boolean isVerified = userService.verifyToken(token);
//
//        if (isVerified) {
//            return ResponseEntity.ok("Email verified successfully!");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
//        }
//    }
//}

import com.sanda.sandaenvmonitor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

//@RestController
//@RequestMapping("/api/users")
//public class UserEmailController {
//
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/send-verification-code")
//    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//
//        // 生成6位随机验证码
//        String verificationCode = String.valueOf(new Random().nextInt(899999) + 100000);
//
//        // 发送验证码到用户邮箱
//        emailService.sendVerificationCode(email, verificationCode);
//
//        return ResponseEntity.ok(Map.of("message", "验证码已发送到您的邮箱。"));
@RestController
@RequestMapping("/api/users")
public class UserEmailController {

    @Autowired
    private EmailService emailService;

    // 用于存储验证码的临时存储（可以使用内存、Redis等）
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    @PostMapping("/sendverificationcode")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        // 验证邮箱格式
        if (!isValidEmail(email)) {
            return ResponseEntity.badRequest().body(Map.of("message", "无效的邮箱地址."));
        }

        // 生成6位随机验证码
        String verificationCode = String.valueOf(new Random().nextInt(899999) + 100000);

        try {
            // 发送验证码到用户邮箱
            emailService.sendVerificationCode(email, verificationCode);
            // 将验证码存储在内存中，您可以使用Redis或数据库来持久化存储
            verificationCodes.put(email, verificationCode);

            return ResponseEntity.ok(Map.of("message", "验证码已发送到您的邮箱。"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "发送验证码失败，请稍后重试。"));
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public boolean verifyCode(String email, String inputCode) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(inputCode);
    }

}
