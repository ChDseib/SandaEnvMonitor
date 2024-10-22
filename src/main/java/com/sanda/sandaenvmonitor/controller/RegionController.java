package com.sanda.sandaenvmonitor.controller;

import com.sanda.sandaenvmonitor.model.Region;
import com.sanda.sandaenvmonitor.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/search")
    public List<Region> searchRegions(@RequestParam("query") String query) {
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("查询参数不能为空");
        }
        return regionService.searchRegions(query);
    }
}
