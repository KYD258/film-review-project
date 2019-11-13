package com.fff.ErrorMsgFallBack;

import com.fff.dao.SearchInterface;
import com.fff.domain.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceFallback implements SearchInterface {
    private Logger logger = LoggerFactory.getLogger(MessageServiceFallback.class);
    @Override
    public Boolean saveCommodityToEs(Commodity commodity) {
        logger.info("消息接口繁忙，请稍后再试");
        return null;
    }

    @Override
    public Boolean deleteCommodityFromEs(Integer id) {
        logger.info("消息接口繁忙，请稍后再试");
        return null;
    }

    @Override
    public Boolean updateCommodityInEs(Commodity commodity) {
        logger.info("消息接口繁忙，请稍后再试");
        return null;
    }
}
