package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIn {

    private final UserRepository userRepository;

    public UserServiceIn(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean setDefaultCity(Long userId, Long regionId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setDefaultCity(regionId);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // 其他方法...
}