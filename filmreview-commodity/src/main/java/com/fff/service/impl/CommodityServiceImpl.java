package com.fff.service.impl;

import com.alibaba.fastjson.JSON;
import com.fff.dao.CommodityMapper;
import com.fff.domain.Commodity;
import com.fff.service.CommodityService;
import com.fff.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public boolean saveCommodity(Commodity commodity) {
//        数据入库
        int saveCommodity = commodityMapper.saveCommodity(commodity);
        if (saveCommodity > 0){
//            加入es库中
            return true;
        }
        return false;
    }

    @Override
    public List<Commodity> findCommodity() {
        return commodityMapper.findCommodity();
    }

    @Override
    public boolean deleteCommodityById(Integer id) {
        if (commodityMapper.deleteCommodityById(id) > 0){
//            删除es中该条数据
            return true;
        }
        return false;
    }

    @Override
    public Commodity findCommodityById(Integer id) {
        return commodityMapper.findCommodityById(id);
    }

    @Override
    public boolean updateCommodity(Commodity commodity) {
        if (commodityMapper.updateCommodity(commodity) > 0){
//            修改es中该条数据
            return true;
        }
        return false;
    }

    @Override
    public String getCommodityPicPath(MultipartFile file) {
        if (!Objects.isNull(file) && !file.isEmpty()){
//            String jsonString = JSON.toJSONString(file, true);
            String path = uploadUtils.getPath(file);
            return path;
        }
        return null;
    }
}
