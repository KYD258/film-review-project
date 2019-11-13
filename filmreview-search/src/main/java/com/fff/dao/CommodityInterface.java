package com.fff.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "filmreview-commodity")
public interface CommodityInterface {

    @GetMapping("/commodity/getCommodityData")
    String getCommodityData();
}
