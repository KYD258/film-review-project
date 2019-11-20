package com.fff.controller;

import com.fff.Responses.CommodityResponse;
import com.fff.commons.R;
import com.fff.domain.Commodity;
import com.fff.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/commodity")
public class CommodityController{

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/getCommodityData/{page}/{size}")
    public CommodityResponse getCommodityData(@PathVariable("page") Integer page, @PathVariable("size")Integer size){
        CommodityResponse commodityResponse = commodityService.findCommodity(page, size);
        return commodityResponse;
    }

    @RequestMapping("/saveCommodity")
    public R saveCommodity(@RequestBody Commodity commodity){
        if (commodityService.saveCommodity(commodity)){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping(value = "/deleteCommodity",method = RequestMethod.POST)
    public R deleteCommodity(@RequestParam("id") Integer commodityId){
        if (commodityService.deleteCommodityById(commodityId)){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/findCommodityById/{id}")
    public Commodity findCommodityById(@PathVariable("id") Integer id){
        Commodity commodity = commodityService.findCommodityById(id);
        return commodity;
    }

    @RequestMapping("/updateCommodity")
    public R updateCommodity(@RequestBody Commodity commodity){
        System.out.println(commodity.getOutTime());
        if (commodityService.updateCommodity(commodity)){
            return R.ok();
        }
        return R.error();
    }

//  获取图片地址，前端判空
    @RequestMapping("/getCommodityPicPath")
    public String getCommodityPicPath(MultipartFile file){
        String path = commodityService.getCommodityPicPath(file);
        return path;
    }
}
