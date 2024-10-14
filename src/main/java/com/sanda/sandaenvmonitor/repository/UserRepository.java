// src/main/java/com/sanda/sandaenvmonitor/repository/UserRepository.java

package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}