package com.fff.dao;

import com.fff.ErrorFallBack.CommodityFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "filmreview-commodity", fallback = CommodityFallback.class)
public interface CommodityInterface {

    @GetMapping("/commodity/getCommodityData")
    String getCommodityData();
}
