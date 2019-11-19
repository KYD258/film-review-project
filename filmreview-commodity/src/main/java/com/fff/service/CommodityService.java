package com.fff.service;

import com.fff.Responses.CommodityResponse;
import com.fff.domain.Commodity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommodityService{

    boolean saveCommodity(Commodity commodity);
//  查询所有
    CommodityResponse findCommodity(Integer page, Integer size);
//  根据id删除
    boolean deleteCommodityById(Integer id);
//  修改
    boolean updateCommodity(Commodity commodity);
//  获取图片地址
    String getCommodityPicPath(MultipartFile file);
//  根据id查一条
    Commodity findCommodityById(Integer id);

}
