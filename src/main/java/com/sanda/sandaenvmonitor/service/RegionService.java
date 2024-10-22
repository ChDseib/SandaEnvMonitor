package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> searchRegions(String query) {
        return regionRepository.findByNameContainingIgnoreCase(query);
    }

    public Region getRegionById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }
}