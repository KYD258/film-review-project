package com.fff.service;

import com.fff.Responses.CommodityResponse;
import com.fff.domain.Commodity;

import java.io.IOException;
import java.util.List;

public interface CommoditySearchService {
//  添加索引
    void createIndex();
//  导入数据
    void toEs();
//  增加商品
    Boolean saveCommodityToEs(Commodity commodity);
//  删除商品
    Boolean deleteCommodityFromEs(Integer id);
//  修改商品
    Boolean updateCommodityInEs(Commodity commodity) throws IOException, InterruptedException;
//  es搜索商品
    CommodityResponse selectCommodityByPage(Integer page, Integer size, String keys);
}
