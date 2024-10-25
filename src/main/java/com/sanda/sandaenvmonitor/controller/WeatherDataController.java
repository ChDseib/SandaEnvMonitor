// src/main/java/com/sanda/sandaenvmonitor/controller/WeatherDataController.java

package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.WeatherData;
import com.sanda.sandaenvmonitor.service.UserService;
import com.sanda.sandaenvmonitor.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*") // 配置CORS，允许所有来源访问API
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    private UserService userService; // 假设已实现 UserService，用于获取用户信息

    @GetMapping("/current")
    public WeatherData getCurrentWeather() {
        return weatherDataService.getCurrentWeather();
    }


    @GetMapping("/future")
    public ResponseEntity<List<WeatherData>> getFutureWeather() {
        String city = String.valueOf(userService.getCurrentUser().getDefaultCity());
        List<WeatherData> futureWeather = weatherDataService.getFutureWeather(city);
        return ResponseEntity.ok(futureWeather);
    }

    @GetMapping("/all")
    public List<WeatherData> getAllWeatherData() {
        return weatherDataService.getAllWeatherData();
    }

    // 添加更多API接口，如刷新数据、导入数据等
}