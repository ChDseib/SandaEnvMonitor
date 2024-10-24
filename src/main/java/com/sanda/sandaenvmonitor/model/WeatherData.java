// src/main/java/com/sanda/sandaenvmonitor/model/WeatherData.java

package com.sanda.sandaenvmonitor.model;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "weather_data")
public class WeatherData implements Serializable {

    @Id
    private String id;
    @Column(name = "city")
    private String city;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "fx_link")
    private String fxLink;

    @Column(name = "fx_date")
    private LocalDate fxDate;

    @Column(name = "sunrise")
    private LocalTime sunrise;

    @Column(name = "sunset")
    private LocalTime sunset;

    @Column(name = "moonrise")
    private LocalTime moonrise;

    @Column(name = "moonset")
    private LocalTime moonset;

    @Column(name = "moon_phase")
    private String moonPhase;

    @Column(name = "moon_phase_icon")
    private String moonPhaseIcon;

    @Column(name = "temp_max")
    private Integer tempMax;

    @Column(name = "temp_min")
    private Integer tempMin;

    @Column(name = "icon_day")
    private String iconDay;

    @Column(name = "text_day")
    private String textDay;

    @Column(name = "icon_night")
    private String iconNight;

    @Column(name = "text_night")
    private String textNight;

    @Column(name = "wind_360_day")
    private Integer wind360Day;

    @Column(name = "wind_dir_day")
    private String windDirDay;

    @Column(name = "wind_scale_day")
    private String windScaleDay;

    @Column(name = "wind_speed_day")
    private Integer windSpeedDay;

    @Column(name = "wind_360_night")
    private Integer wind360Night;

    @Column(name = "wind_dir_night")
    private String windDirNight;

    @Column(name = "wind_scale_night")
    private String windScaleNight;

    @Column(name = "wind_speed_night")
    private Integer windSpeedNight;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "precip")
    private BigDecimal precip;

    @Column(name = "pressure")
    private Integer pressure;

    @Column(name = "visibility")
    private Integer visibility;

    @Column(name = "cloud")
    private Integer cloud;

    @Column(name = "uv_index")
    private Integer uvIndex;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getFxDate() {
        return fxDate;
    }

    public void setFxDate(LocalDate fxDate) {
        this.fxDate = fxDate;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }



    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public LocalTime getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(LocalTime moonrise) {
        this.moonrise = moonrise;
    }

    public LocalTime getMoonset() {
        return moonset;
    }

    public void setMoonset(LocalTime moonset) {
        this.moonset = moonset;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public String getMoonPhaseIcon() {
        return moonPhaseIcon;
    }

    public void setMoonPhaseIcon(String moonPhaseIcon) {
        this.moonPhaseIcon = moonPhaseIcon;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public String getIconDay() {
        return iconDay;
    }

    public void setIconDay(String iconDay) {
        this.iconDay = iconDay;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getIconNight() {
        return iconNight;
    }

    public void setIconNight(String iconNight) {
        this.iconNight = iconNight;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public Integer getWind360Day() {
        return wind360Day;
    }

    public void setWind360Day(Integer wind360Day) {
        this.wind360Day = wind360Day;
    }

    public String getWindDirDay() {
        return windDirDay;
    }

    public void setWindDirDay(String windDirDay) {
        this.windDirDay = windDirDay;
    }

    public String getWindScaleDay() {
        return windScaleDay;
    }

    public void setWindScaleDay(String windScaleDay) {
        this.windScaleDay = windScaleDay;
    }

    public Integer getWindSpeedDay() {
        return windSpeedDay;
    }

    public void setWindSpeedDay(Integer windSpeedDay) {
        this.windSpeedDay = windSpeedDay;
    }

    public Integer getWind360Night() {
        return wind360Night;
    }

    public void setWind360Night(Integer wind360Night) {
        this.wind360Night = wind360Night;
    }

    public String getWindDirNight() {
        return windDirNight;
    }

    public void setWindDirNight(String windDirNight) {
        this.windDirNight = windDirNight;
    }

    public String getWindScaleNight() {
        return windScaleNight;
    }

    public void setWindScaleNight(String windScaleNight) {
        this.windScaleNight = windScaleNight;
    }

    public Integer getWindSpeedNight() {
        return windSpeedNight;
    }

    public void setWindSpeedNight(Integer windSpeedNight) {
        this.windSpeedNight = windSpeedNight;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getPrecip() {
        return precip;
    }

    public void setPrecip(BigDecimal precip) {
        this.precip = precip;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getCloud() {
        return cloud;
    }

    public void setCloud(Integer cloud) {
        this.cloud = cloud;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }
}