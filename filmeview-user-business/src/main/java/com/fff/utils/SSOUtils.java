package com.fff.utils;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
public class SSOUtils {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpSession session;

    public  void login(HttpServletRequest req, HttpServletResponse resp,String phone){
        String ck= CookieUtils.getCookieValue(req,"token");
        if(ck!=null && ck.length()>0){
        }else {
            String id = session.getId();
            CookieUtils.setCookie(req,resp,"token",id,300);
        }
    }
    public void loginOut(HttpServletRequest req, HttpServletResponse resp){
        String ck=CookieUtils.getCookieValue(req,"token");
        if(ck!=null && ck.length()>0){
            //失效
            CookieUtils.setCookie(req,resp,"token","",0);
        }
    }
    public String checkToken(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ck=CookieUtils.getCookieValue(req,"token");
        String callback = req.getParameter("callback");
        String json= "{'id':'"+ck+"','name':'"+redisTemplate.opsForValue().get("token:")+"'}";
        resp.getWriter().print(callback+"("+json+")");
        return json;
    }

//    @RabbitListener(queues = "topic.message")
//    public void saveUser(HttpServletRequest req, HttpServletResponse resp,String token){
//        String ck= CookieUtils.getCookieValue(req,"token");
//        if(ck!=null && ck.length()>0){
//        }else {
//            String id = session.getId();
//            CookieUtils.setCookie(req,resp,"token",id,300);
//            System.out.println("已经存cookie，name：token，value："+id);
//        }
//    }
}
