package com.fff.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.catalina.manager.Constants.CHARSET;
@Component
public class AliOauth2Utils {
    @Value("${APP_ID}")
    private String appId;
    @Value("${APP_PRIVATE_KEY}")
    private String privateKey;
    @Value("${ALIPAY_PUBLIC_KEY}")
    private String publicKey;

    /**
     *
     * @param code
     * @return
     * @throws AlipayApiException
     */

    public  AlipayOpenAuthTokenAppResponse getToken(String code) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", appId,privateKey,
                "json",CHARSET, publicKey,"RSA2");

        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();

        request.setBizContent("｛\"grant_type\":\"authorization_code\",\"code\":\""+code+"\"｝");

        AlipayOpenAuthTokenAppResponse response =alipayClient.execute(request);


        return response;
    }

    /**
     *
     * @param token
     * @return
     * @throws AlipayApiException
     */

    public AlipayUserInfoShareResponse getUserInfo(String token) throws AlipayApiException {
        AlipayClient alipayClient=new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", appId,privateKey,
                "json",CHARSET, publicKey,"RSA2");

        AlipayUserInfoShareRequest alipayUserInfoShareRequest=new AlipayUserInfoShareRequest();
        alipayUserInfoShareRequest.setApiVersion("1.0");
        AlipayUserInfoShareResponse alipayUserInfoShareResponse=alipayClient.execute(alipayUserInfoShareRequest,token);
        return alipayUserInfoShareResponse;

    }


}

