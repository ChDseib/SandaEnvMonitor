package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.User;
import com.sanda.sandaenvmonitor.model.UserPrincipal;
import com.sanda.sandaenvmonitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // 返回自定义的 UserPrincipal，包含 User 实体
        return new UserPrincipal(user);
    }
}
