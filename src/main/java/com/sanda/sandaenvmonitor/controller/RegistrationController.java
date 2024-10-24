// src/main/java/com/sanda/sandaenvmonitor/controller/RegistrationController.java

package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.service.UserServiceInterdace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceInterdace userServiceInterdace;

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
            user.setEnabled(true);  // 默认启用
            userServiceInterdace.registerUser(user);
            return "redirect:/login?success";  // 注册成功后重定向到登录页面
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";  // 注册失败时回到注册页面
        }
    }
}