// src/main/java/com/sanda/sandaenvmonitor/service/WeatherDataService.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.WeatherData;

import java.util.List;

public interface WeatherDataService {
    List<WeatherData> getAllWeatherData();
    WeatherData getCurrentWeather();
    List<WeatherData> getFutureWeather();
    // 根据需求添加更多方法
}