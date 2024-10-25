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

    // 修改后的方法：根据 regionid 获取未来七天的天气数据
    @Override
    public List<WeatherData> getFutureWeather(String city) {
        return weatherDataRepository.findTop7ByCityOrderByFxDateDesc(city);
    }
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