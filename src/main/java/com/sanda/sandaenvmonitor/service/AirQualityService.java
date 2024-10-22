package com.sanda.sandaenvmonitor.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanda.sandaenvmonitor.model.AirQuality;
import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.repository.AirQualityRepository;
import com.sanda.sandaenvmonitor.repository.RegionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AirQualityService {

    @Value("${qweather.api.url}")
    private String apiUrl;

    @Value("${qweather.api.key}")
    private String apiKey;

    private final RegionRepository regionRepository;
    private final AirQualityRepository airQualityRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    public AirQualityService(RegionRepository regionRepository, AirQualityRepository airQualityRepository) {
        this.regionRepository = regionRepository;
        this.airQualityRepository = airQualityRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Scheduled(cron = "0 0 * * * ?") // 每小时执行一次
    public void fetchAndStoreAirQualityData() {
        List<Region> regions = regionRepository.findAll();
        for (Region region : regions) {
            try {
                String url = String.format("%s?location=%d&key=%s", apiUrl, region.getId(), apiKey);
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    String body = response.getBody();
                    JsonNode root = objectMapper.readTree(body);
                    if ("200".equals(root.path("code").asText())) {
                        JsonNode now = root.path("now");
                        AirQuality airQuality = new AirQuality();
                        airQuality.setId(Long.valueOf(region.getId().toString()));
                        airQuality.setUpdateTime(parseDateTime(root.path("updateTime").asText()));
                        airQuality.setFxLink(root.path("fxLink").asText());
                        airQuality.setPubTime(parseDateTime(now.path("pubTime").asText()));
                        airQuality.setAqi(now.path("aqi").asText());
                        airQuality.setLevel(now.path("level").asText());
                        airQuality.setCategory(now.path("category").asText());
                        airQuality.setPrimaryElement(now.path("primary").asText());
                        airQuality.setPm10(now.path("pm10").asText());
                        airQuality.setPm2p5(now.path("pm2p5").asText());
                        airQuality.setNo2(now.path("no2").asText());
                        airQuality.setSo2(now.path("so2").asText());
                        airQuality.setCo(now.path("co").asText());
                        airQuality.setO3(now.path("o3").asText());

                        airQualityRepository.save(airQuality);
                        System.out.println("Saved air quality data for region: " + region.getName());
                    } else {
                        System.err.println("API返回错误代码: " + root.path("code").asText());
                    }
                } else {
                    System.err.println("HTTP请求失败，状态码: " + response.getStatusCode());
                }
            } catch (Exception e) {
                System.err.println("获取或存储空气质量数据时出错: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        // 示例格式: "2024-10-22T21:59+08:00"
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}