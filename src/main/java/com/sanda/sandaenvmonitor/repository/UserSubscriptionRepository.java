package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUserId(Long userId);
    boolean existsByUserIdAndRegionId(Long userId, Long regionId);
}