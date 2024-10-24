package com.sanda.sandaenvmonitor.model;

import java.util.List;

public class WeatherDTO {
    private List<WeatherData> weatherInfo; // 假设data.daily是一个天气信息的数组
    private String fxLink;

    // getters 和 setters
    public List<WeatherData> getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(List<WeatherData> weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }
}