package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByNameContainingIgnoreCase(String name);
    @Query("SELECT r FROM Region r WHERE r.name = :name")
    Region getRegionByCN(@Param("name") String name);
}