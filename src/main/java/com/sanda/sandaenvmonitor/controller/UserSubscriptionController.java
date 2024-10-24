package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.model.UserPrincipal;
import com.sanda.sandaenvmonitor.service.RegionService;
import com.sanda.sandaenvmonitor.service.UserService;
import com.sanda.sandaenvmonitor.service.UserSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscriptions")
public class UserSubscriptionController {

    private final UserSubscriptionService subscriptionService;
    private final RegionService regionService;
    private final UserService userService;

    public UserSubscriptionController(UserSubscriptionService subscriptionService, RegionService regionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.regionService = regionService;
        this.userService = userService;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();  // 将principal转换为UserPrincipal
        return userPrincipal.getUser();  // 获取自定义的User实体
    }

    @GetMapping
    public Map<String, Object> getUserSubscriptions() {
        User currentUser = getCurrentUser();
        Long userId = currentUser.getId();
        List<Region> subscriptions = subscriptionService.getSubscriptionsByUserId(userId).stream()
                .map(sub -> regionService.getRegionById(sub.getRegionId()))
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("subscriptions", subscriptions);
        response.put("defaultCityId", currentUser.getDefaultCity());
        return response;
    }

    @PostMapping
    public String addSubscription(@RequestParam Long regionId) {
        User currentUser = getCurrentUser();
        Long userId = currentUser.getId();
        boolean success = subscriptionService.addSubscription(userId, regionId);
        if (success) {
            return "订阅成功";
        } else {
            return "已经订阅过该城市";
        }
    }
    @DeleteMapping
    public String deleteSubscription(@RequestParam Long regionId) {
        User currentUser = getCurrentUser();
        Long userId = currentUser.getId();
        boolean success = subscriptionService.deleteSubscription(userId, regionId);
        if (success) {
            return "删除订阅成功";
        } else {
            return "订阅不存在";
        }
    }

    @PostMapping("/default")
    public ResponseEntity<String> setDefaultCity(@RequestParam Long regionId) {
        User currentUser = getCurrentUser();
        boolean success = userService.setDefaultCity(currentUser.getId(), regionId);
        if (success) {
            return ResponseEntity.ok("默认城市设置成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("设置默认城市失败");
        }
    }
}
