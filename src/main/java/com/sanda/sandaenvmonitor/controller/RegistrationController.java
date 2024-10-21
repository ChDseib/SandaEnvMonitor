package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.service.UserService;
import com.sanda.sandaenvmonitor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            Model model) {

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);  // 明文存储，服务层将加密
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setEnabled(false);  // 默认禁用
            userService.registerUser(user);

            // 发送验证邮件
            emailService.sendVerificationEmail(user);

            return "redirect:/verify?email=" + email;  // 注册成功后重定向到验证页面
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";  // 注册失败时回到注册页面
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") String token, Model model) {
        try {
            userService.enableUser(token);
            model.addAttribute("message", "验证成功，请前往登录页面。");
            return "verify";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "verify";
        }
    }
}
