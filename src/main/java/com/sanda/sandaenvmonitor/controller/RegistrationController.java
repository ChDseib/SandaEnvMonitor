// src/main/java/com/sanda/sandaenvmonitor/controller/RegistrationController.java

package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.service.EmailService;
import com.sanda.sandaenvmonitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    private EmailService emailService;

    @Autowired
    private UserEmailController userEmailController; // 注入 UserEmailController

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
            @RequestParam("verificationCode") String verificationCode,
            Model model) {

        if (!userEmailController.verifyCode(email, verificationCode)) { // 调用 UserEmailController 的验证方法
            model.addAttribute("error", "无效或过期的验证码。");
            return "register";  // 注册失败时回到注册页面
        }

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);  // 明文存储，服务层将加密
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setEnabled(true);  // 默认启用
            userService.registerUser(user);

            return "redirect:/login?success";  // 注册成功后重定向到登录页面
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";  // 注册失败时回到注册页面
        }
    }
}
