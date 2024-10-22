package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.model.UserPrincipal;
import com.sanda.sandaenvmonitor.service.RegionService;
import com.sanda.sandaenvmonitor.service.UserSubscriptionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscriptions")
public class UserSubscriptionController {

    private final UserSubscriptionService subscriptionService;
    private final RegionService regionService;

    public UserSubscriptionController(UserSubscriptionService subscriptionService, RegionService regionService) {
        this.subscriptionService = subscriptionService;
        this.regionService = regionService;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();  // 将principal转换为UserPrincipal
        return userPrincipal.getUser();  // 获取自定义的User实体
    }

    @GetMapping
    public List<Region> getUserSubscriptions() {
        User currentUser = getCurrentUser();
        Long userId = currentUser.getId();
        return subscriptionService.getSubscriptionsByUserId(userId).stream()
                .map(sub -> regionService.getRegionById(sub.getRegionId()))
                .collect(Collectors.toList());
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
}
