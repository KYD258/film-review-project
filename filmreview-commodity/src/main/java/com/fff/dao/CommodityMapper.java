package com.fff.dao;

import com.fff.domain.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {

//    添加周边商品
    int saveCommodity(Commodity commodity);
//    删除周边商品
    int deleteCommodityById(Integer commodityId);
//    查询全部
    List<Commodity> findCommodity();
//    查询一个
    Commodity findCommodityById(Integer commodityId);
//    修改周边商品
    int updateCommodity(Commodity commodity);
}
