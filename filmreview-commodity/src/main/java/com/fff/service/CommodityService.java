package com.fff.service;

import com.fff.domain.Commodity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommodityService{

    boolean saveCommodity(Commodity commodity);
//  查询所有
    List<Commodity> findCommodity();
//  根据id删除
    boolean deleteCommodityById(Integer id);
//  修改
    boolean updateCommodity(Commodity commodity);
//  获取图片地址
    String getCommodityPicPath(MultipartFile file);
//  根据id差一条
    Commodity findCommodityById(Integer id);

}
