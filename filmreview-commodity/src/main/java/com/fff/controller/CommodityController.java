package com.fff.controller;

import com.fff.commons.R;
import com.fff.domain.Commodity;
import com.fff.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/getCommodityData")
    public List<Commodity> getCommodityData(){
        List<Commodity> commodityList = commodityService.findCommodity();
        return commodityList;
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
