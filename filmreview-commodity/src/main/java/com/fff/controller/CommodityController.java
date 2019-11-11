package com.fff.controller;

import com.fff.commons.R;
import com.fff.domain.Commodity;
import com.fff.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/deleteCommodity")
    public R deleteCommodity(@PathVariable("id") Integer id){
        if (commodityService.deleteCommodityById(id)){
            R.ok();
        }
        return R.error();
    }

    @RequestMapping("/findCommodityById")
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
