package com.fff.Responses;

import com.fff.domain.Commodity;
import lombok.Data;

import java.util.List;

@Data
public class CommodityResponse {
    private List<Commodity> commodityList;
    private Long total;
}
