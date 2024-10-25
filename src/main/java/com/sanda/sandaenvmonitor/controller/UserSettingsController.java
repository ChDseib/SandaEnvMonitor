package com.sanda.sandaenvmonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSettingsController {

    @GetMapping("/settings")
    public String showSettingsPage() {
        return "settings"; // 返回模板名称，不需要加 .html
    }
}