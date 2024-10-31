// src/main/java/com/sanda/sandaenvmonitor/controller/RegistrationController.java

package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
    // 发送验证码的API
    @PostMapping("/api/users/send-verification-code")
    @ResponseBody
    public Response sendVerificationCode(@RequestBody EmailRequest emailRequest) {
        try {
            userService.sendVerificationCode(emailRequest.getEmail());
            return new Response(true, "验证码已发送到您的邮箱。");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @ModelAttribute UserRegistrationForm form,
            Model model) {

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);  // 明文存储，服务层将加密
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setEnabled(true);  // 默认启用
            userService.registerUser(user, form.getVerificationCode());
            return "redirect:/login?success";  // 注册成功后重定向到登录页面
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";  // 注册失败时回到注册页面
        }
    }
    public static class EmailRequest {
        private String email;

        // Getter and Setter
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    // 内部类用于标准化API响应
    public static class Response {
        private boolean success;
        private String message;

        public Response() {}

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        // Getter and Setter
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // 内部类用于接收注册表单数据
    public static class UserRegistrationForm {
        private String username;
        private String password;
        private String email;
        private String phoneNumber;
        private String verificationCode;

        // Getters and Setters

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }
    }
}