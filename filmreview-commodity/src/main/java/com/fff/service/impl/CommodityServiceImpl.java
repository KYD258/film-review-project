package com.fff.service.impl;

import com.fff.Responses.CommodityResponse;
import com.fff.dao.CommodityMapper;
import com.fff.dao.SearchInterface;
import com.fff.domain.Commodity;
import com.fff.service.CommodityService;
import com.fff.utils.UploadUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private SearchInterface searchInterface;
    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public boolean saveCommodity(Commodity commodity) {
//        数据入库
        int saveCommodity = commodityMapper.saveCommodity(commodity);
        if (saveCommodity > 0){
//            加入es库中
            Boolean toEs = searchInterface.saveCommodityToEs(commodity);
            System.out.println(toEs);
            if (toEs!=null && toEs){
                return true;
            }
        }
        return false;
    }

    @Override
    public CommodityResponse findCommodity(Integer page, Integer size) {
        Page<Object> startPage = PageHelper.startPage(page, size);
        PageInfo<Commodity> pageInfo = new PageInfo<>(commodityMapper.findCommodity());
        CommodityResponse commodityResponse = new CommodityResponse();
        commodityResponse.setCommodityList(pageInfo.getList());
        commodityResponse.setTotal(pageInfo.getTotal());
        return commodityResponse;
    }

    @Override
    public boolean deleteCommodityById(Integer id) {
        if (commodityMapper.deleteCommodityById(id) > 0){
//            删除es中该条数据
            Boolean fromEs = searchInterface.deleteCommodityFromEs(id);
            System.out.println(fromEs+"--------------");
            if (fromEs != null && fromEs){
                return true;
            }
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
            Boolean inEs = searchInterface.updateCommodityInEs(commodity);
            System.out.println(inEs+"--------------");
            if (inEs != null && inEs){
                return true;
            }
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
