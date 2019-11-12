package com.fff.controller;

import com.fff.Responses.CommodityResponse;
import com.fff.domain.Commodity;
import com.fff.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/toEs")
    public void toEs(){
        searchService.createIndex();
        searchService.toEs();
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

    @RequestMapping("/searchCommodity/{keys}/{page}/{size}")
    public CommodityResponse searchCommodity(@PathVariable("keys")String keys,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
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