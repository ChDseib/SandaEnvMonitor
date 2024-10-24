package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.model.WeatherDTO;
import com.sanda.sandaenvmonitor.model.WeatherData;
import com.sanda.sandaenvmonitor.repository.RegionRepository;
import com.sanda.sandaenvmonitor.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {



    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private RegionRepository  regionRepository;


    @PostMapping("/search")
    public HashMap<String,Object> getCode(@RequestBody Map<String, String> map) {
        HashMap<String,Object> result = new HashMap<>();
        String code = "";
        String regionName = (String) map.get("region");
        Region regionByCN = regionRepository.getRegionByCN(regionName);
        if (regionByCN!=null){
            code = regionByCN.getId().toString();
        }else{
            //当返回404时表示无该地区数据
            code = "404";
        }
        result.put("code",code);
        return result;


    }
    @PostMapping("/sevenday")
    public void saveData(@RequestBody WeatherDTO dto) {
        List<WeatherData> list = dto.getWeatherInfo();
        String fxLink = dto.getFxLink();
        //拆分天气链接进行id的拼接
        String fxLinkInfo = fxLink.split("/")[5].split(".html")[0];
        for (WeatherData daily : list) {
            /**
             * id规则
             * 地区编码+获取的天气日期
             * 例如：10101010020241022
             * 后续可通过weather-daily表的id来获取地区和预防天气的重复获取
             */
            String[] split = daily.getFxDate().toString().split("-");
            String weatherDate = split[0].concat(split[1]).concat(split[2]);
            String id = fxLinkInfo.split("-")[1].concat(weatherDate);
            String city = fxLinkInfo.split("-")[1];
            boolean b = checkIsExit(id);
            if (!b) {
                continue;
            }else{
                daily.setId(id);
                daily.setCity(city);
                daily.setFxLink(fxLink);
                daily.setUpdateTime(LocalDateTime.now());
                weatherRepository.save(daily);
            }

        }

    }

    /*
    * 检查是否已经存在该地区的天气信息
     */
    public boolean checkIsExit(String id){
        WeatherData referenceById = weatherRepository.getWeatherById(id);
        if (referenceById!=null){
            return false;
        }

        return true;
    }
}
