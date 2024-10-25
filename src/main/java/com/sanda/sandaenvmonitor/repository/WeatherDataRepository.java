// src/main/java/com/sanda/sandaenvmonitor/repository/WeatherDataRepository.java

package com.sanda.sandaenvmonitor.repository;

import com.sanda.sandaenvmonitor.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findTop7ByOrderByFxDateDesc();
    List<WeatherData> findTop7ByCityOrderByFxDateDesc(String city);
    List<WeatherData> findTopByOrderByFxDateDesc();
    // 根据需求添加更多查询方法
}