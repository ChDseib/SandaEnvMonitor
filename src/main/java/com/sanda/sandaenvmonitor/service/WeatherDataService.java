// src/main/java/com/sanda/sandaenvmonitor/service/WeatherDataService.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.WeatherData;

import java.util.List;

public interface WeatherDataService {
    // 修改后的方法：根据 regionid 获取未来七天的天气数据
    List<WeatherData> getFutureWeather(String regionid);

    List<WeatherData> getAllWeatherData();
    WeatherData getCurrentWeather();
    List<WeatherData> getFutureWeather();
    // 根据需求添加更多方法
}