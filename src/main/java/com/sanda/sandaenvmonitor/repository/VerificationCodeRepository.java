package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String email);
    void deleteByEmail(String email);
}