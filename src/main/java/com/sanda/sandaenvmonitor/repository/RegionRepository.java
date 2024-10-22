package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByNameContainingIgnoreCase(String name);

}