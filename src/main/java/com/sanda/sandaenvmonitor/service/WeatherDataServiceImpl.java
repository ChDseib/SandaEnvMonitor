// src/main/java/com/sanda/sandaenvmonitor/service/WeatherDataServiceImpl.java

package com.sanda.sandaenvmonitor.service;

import com.sanda.sandaenvmonitor.model.WeatherData;
import com.sanda.sandaenvmonitor.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Override
    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }

    @Override
    public WeatherData getCurrentWeather() {
        // 假设最新的一条数据为当前天气
        return weatherDataRepository.findTopByOrderByFxDateDesc().stream().findFirst().orElse(null);
    }

    @Override
    public List<WeatherData> getFutureWeather() {
        // 获取未来七天的预报数据
        return weatherDataRepository.findTop7ByOrderByFxDateDesc();
    }

    // 实现更多方法
}