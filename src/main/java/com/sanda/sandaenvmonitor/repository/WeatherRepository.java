// src/main/java/com/sanda/sandaenvmonitor/repository/UserRepository.java

package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherRepository extends JpaRepository<WeatherData, String> {

    @Query("SELECT d FROM WeatherData d WHERE d.id = :id")
    WeatherData getWeatherById(@Param("id") String id);

}