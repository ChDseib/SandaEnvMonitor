package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, String> {
    Optional<AirQuality> findById(String id);
}