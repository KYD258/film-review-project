package com.fff.shiro;

import com.fff.dao.UserRepository;
import com.fff.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String phone = (String) authenticationToken.getPrincipal();
        String password ="";
        Object userByPhone = redisTemplate.opsForHash().get("user", phone);
        if (userByPhone!=null&&!userByPhone.toString().equals("empty")){
            User userByPhone1 = (User) userByPhone;
            String passWord = userByPhone1.getPassWord();
        }
        User user=new User();
        user.setPhone(phone);
        Example<User> example=Example.of(user);
        Optional<User> userOptional=userRepository.findOne(example);
        if (userOptional!=null){
            password = userOptional.get().getPassWord();
        }
        redisTemplate.opsForHash().put("user",user.getPhone(),"empty");
        //设置固定时间删除，定时器
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                redisTemplate.opsForHash().delete("user", user.getPhone());

            }
        },5000);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(phone, password, ByteSource.Util.bytes("hello"), getName());
        return simpleAuthenticationInfo;
    }
}
