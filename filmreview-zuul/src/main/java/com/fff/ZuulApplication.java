package com.fff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@SpringBootApplication
@EnableZuulProxy
@EnableRedisHttpSession
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class);
    }


//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
//        //cookie名字
//        defaultCookieSerializer.setCookieName("token");
//        //域
//        defaultCookieSerializer.setDomainName("localhost:8001");
//        //存储路径设为根路径，同一域名下多个项目共享该cookie
//        defaultCookieSerializer.setCookiePath("/");
//        return defaultCookieSerializer;
//    }
}
