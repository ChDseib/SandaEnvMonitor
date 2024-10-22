package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.service.RegionService;
import com.sanda.sandaenvmonitor.service.UserSubscriptionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping
    public List<Region> getUserSubscriptions(@AuthenticationPrincipal User userDetails) {
        Long userId = userDetails.getId();
        return subscriptionService.getSubscriptionsByUserId(userId).stream()
                .map(sub -> regionService.getRegionById(sub.getRegionId()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String addSubscription(@AuthenticationPrincipal User userDetails, @RequestParam Long regionId) {
        Long userId = userDetails.getId();
        boolean success = subscriptionService.addSubscription(userId, regionId);
        if (success) {
            return "订阅成功";
        } else {
            return "已经订阅过该城市";
        }
    }
}