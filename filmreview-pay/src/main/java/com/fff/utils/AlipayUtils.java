package com.fff.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.fff.config.AlipayConfig;
import com.fff.domain.Commodity;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AlipayUtils {

    /**
     *
     * @param commodity
     * @param orderNum
     * @return
     * @throws AlipayApiException
     */
    public String pay(Commodity commodity, String orderNum) throws AlipayApiException {
        Random random=new Random();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json","utf-8",AlipayConfig.alipay_public_key,"RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+orderNum+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+commodity.getCommodityPrice()+"," +
                "    \"subject\":\""+commodity.getCommodityName()+"\"," +
                "    \"body\":\""+commodity.getCommodityName()+"\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");
        String form="";
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单        System.out.println(response.getBody());
        return form;
    }
}
