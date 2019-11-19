package com.fff.controller;

import com.fff.Responses.CommodityResponse;
import com.fff.domain.Commodity;
import com.fff.service.CommoditySearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/search")
public class CommoditySearchController {

    @Autowired
    private CommoditySearchService searchService;

    @RequestMapping("/toEs")
    public void toEs(){
        searchService.createIndex();
//        searchService.toEs();
    }

    @RequestMapping("/saveCommodityToEs")
    @HystrixCommand(fallbackMethod = "getMsgFallBack")
    public Boolean saveCommodityToEs(@RequestBody Commodity commodity){
        return searchService.saveCommodityToEs(commodity);
    }

    @RequestMapping("/deleteCommodityFromEs")
    @HystrixCommand(fallbackMethod = "getMsgFallBackForDelete")
    public Boolean deleteCommodityFromEs(@RequestParam("id") Integer commodityId){
        return searchService.deleteCommodityFromEs(commodityId);
    }

    @RequestMapping("/updateCommodityInEs")
    @HystrixCommand(fallbackMethod = "getMsgFallBack")
    public Boolean updateCommodityInEs(@RequestBody Commodity commodity) throws IOException, InterruptedException {
        return searchService.updateCommodityInEs(commodity);
    }

    @RequestMapping(value = "/searchCommodity",method = RequestMethod.POST)
    public CommodityResponse searchCommodity(@RequestParam("keys") String keys, @RequestParam("page") Integer page, @RequestParam("size")Integer size){
        System.out.println(keys);
        CommodityResponse commodityResponse = searchService.selectCommodityByPage(page, size, keys);
        return commodityResponse;
    }

    public Boolean getMsgFallBack(@RequestBody Commodity commodity){
        return null;
    }

    public Boolean getMsgFallBackForDelete(@RequestParam("id") Integer commodityId){
        return null;
    }
}
