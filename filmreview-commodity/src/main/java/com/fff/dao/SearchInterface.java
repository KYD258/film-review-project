package com.fff.dao;

import com.fff.ErrorMsgFallBack.MessageServiceFallback;
import com.fff.domain.Commodity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "filmreview-search", fallback = MessageServiceFallback.class)
public interface SearchInterface {

    @PostMapping("/search/saveCommodityToEs")
    Boolean saveCommodityToEs(@RequestBody Commodity commodity);

    @PostMapping("/search/deleteCommodityFromEs")
    Boolean deleteCommodityFromEs(@RequestParam("id") Integer commodityId);

    @PostMapping("/search/updateCommodityInEs")
    Boolean updateCommodityInEs(@RequestBody Commodity commodity);
}
