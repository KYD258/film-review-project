package com.fff.ErrorFallBack;

import com.fff.dao.CommodityInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommodityFallback implements CommodityInterface {
    private Logger logger = LoggerFactory.getLogger(CommodityFallback.class);
    @Override
    public String getCommodityData() {
        logger.info("消息接口繁忙，请稍后再试");
        return null;
    }
}
