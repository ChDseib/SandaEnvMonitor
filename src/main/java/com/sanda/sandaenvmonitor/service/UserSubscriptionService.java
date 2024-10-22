package com.sanda.sandaenvmonitor.service;


import com.sanda.sandaenvmonitor.model.UserSubscription;
import com.sanda.sandaenvmonitor.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSubscriptionService {

    private final UserSubscriptionRepository subscriptionRepository;

    public UserSubscriptionService(UserSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<UserSubscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public boolean addSubscription(Long userId, Long regionId) {
        if (subscriptionRepository.existsByUserIdAndRegionId(userId, regionId)) {
            return false; // 已经订阅
        }
        UserSubscription subscription = new UserSubscription();
        subscription.setUserId(userId);
        subscription.setRegionId(regionId);
        subscriptionRepository.save(subscription);
        return true;
    }

    // 其他方法如删除订阅等（如果需要）
}