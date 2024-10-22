package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.AirQuality;
import com.sanda.sandaenvmonitor.repository.AirQualityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/air-quality")
public class AirQualityController {

    private final AirQualityRepository airQualityRepository;

    public AirQualityController(AirQualityRepository airQualityRepository) {
        this.airQualityRepository = airQualityRepository;
    }

    @GetMapping
    public List<AirQuality> getAllAirQuality() {
        return airQualityRepository.findAll();
    }

    @GetMapping("/{regionId}")
    public Optional<AirQuality> getAirQualityByRegion(@PathVariable String regionId) {
        return airQualityRepository.findById(regionId); // 需要在repository中定义对应的方法
    }
}