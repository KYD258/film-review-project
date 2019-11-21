package com.fff.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.fff.service.Oauth2Service;
import com.fff.utils.AliOauth2Utils;
import com.fff.utils.GitOauthUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class Oauth2ServiceImpl implements Oauth2Service {

    @Autowired
    private AliOauth2Utils aliOauth2Utils;
    @Autowired
    private GitOauthUtils gitOauthUtils;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${client_id}")
    private String client_id;
    @Value("${client_secret}")
    private String client_secret;
    @Value("${url}")
    private String url;

    @Override
    public String AliLogin(String code) {
        AlipayOpenAuthTokenAppResponse token =null;
        try {
            token = aliOauth2Utils.getToken(code);
            System.out.println(token+"ali====>");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(token.isSuccess()){
            //拿出用户数据放入mq异步写入数据库

            System.out.println("调用成功");
            return "success";
        } else {
            System.out.println("调用失败");
        }
        return "filed";
    }

    @Override
    public String GitLogin(String code) {
        Map<String,Object> map=new HashMap<>();
        map.put("client_id",client_id);
        map.put("client_secret",client_secret);
        map.put("code",code);
        String token= gitOauthUtils.doPost(url, map);
        String[] split = token.split("&");
        String s1 = split[0];
        String[] split1 = s1.split("=");
        //将token放入mq，用户写入数据库，并且存session
        if (split1[1]!=null){
//          rabbitTemplate.convertAndSend("exchange","topic.message",split1);
            return "success";
        }
        return "faild";
    }
}
