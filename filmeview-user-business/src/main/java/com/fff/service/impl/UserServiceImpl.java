package com.fff.service.impl;

import com.fff.dao.UserMapper;
import com.fff.dao.UserRepository;
import com.fff.domain.Code;
import com.fff.domain.User;
import com.fff.response.UserAndCode;
import com.fff.service.UserService;
import com.fff.sms.SmsSDKDemo;
import com.fff.utils.Md5Utils;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SmsSDKDemo smsSDKDemo;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean isAdmin(User user) {
        Integer admin = userMapper.isAdmin(user.getUserId());
        if (admin==3){
            return true;
        }
        return false;
    }

    @Override
    public Boolean havePms(User user) {
        Integer integer = userMapper.havePms(user.getUserId());
        if (integer==1){
            return true;
        }

        return false;
    }

    @Override
    public String toVip(User user) {
        userMapper.toVip(user.getUserId());

        return "success";
    }

    @Override
    public void regByPhone(Code code) {
        Object code1 = redisTemplate.opsForHash().get("code", code.getPhone());
        if (code==code1){
            User user=new User();
            user.setPhone(code.getPhone());
            user.setUserName(String.valueOf(UUID.randomUUID()));
            user.setUserStatus(0);
            user.setPassWord(Md5Utils.getMd5Password("123456"));
            //暂时不填，七牛云搞好再set，其他为空
//            user.setUserPic();
            //存库，redis
            redisTemplate.opsForHash().put("user",code.getPhone(),user);
            User save = userRepository.save(user);
        }

    }

    @Override
    public String sendCode(String phone) {
        smsSDKDemo.sendCode(phone);
        return "验证码已发送！请注意接收！";
    }

    @Override
    public String register(UserAndCode userAndCode) {
        User user=userAndCode.getUser();
        int code=userAndCode.getCode();
        //判重
        Object user1 = redisTemplate.opsForHash().get("user", user.getPhone());
        if (user1!=null&&!user1.toString().equals("empty")){
            return "用户已注册，请登录";
        }
        Example<User> example=Example.of(user);
        Optional<User> one = userRepository.findOne(example);
        if (one!=null&&one.isPresent()){
            return "用户已注册，请登录";
        }
        Object code1 = redisTemplate.opsForHash().get("code", user.getPhone());
        Integer code2=null;
        if (code1!=null&&!code1.toString().equals("empty")){
            code2=(Integer) code1;
        }
        if (code2!=code){
            return "请核对验证码！";
        }
        user.setUserStatus(0);
        String md5Password = Md5Utils.getMd5Password(user.getPassWord());
        user.setPassWord(md5Password);
        User save = userRepository.save(user);
        redisTemplate.opsForHash().put("user",user.getPhone(),save);
        if (save!=null){
            return "注册成功";
        }
        return "注册失败";
    }

    @Override
    public String loginByPhone(Code code) {
        Object user1 = redisTemplate.opsForHash().get("user", code.getPhone());
        Optional<User> ones =null;
        if (user1!=null&&!user1.equals("empty")){
            User user=new User();
            user.setPhone(code.getPhone());
            Example<User> example = Example.of(user);
            ones = userRepository.findOne(example);
        }
        if (ones==null&&!ones.isPresent()){
            this.regByPhone(code);
        }
        Object code1 = redisTemplate.opsForHash().get("code", code.getPhone());
        Integer code2=null;
        if (code1!=null&&!code1.toString().equals("empty")){
            code2=(Integer)code1;
        }
        int code3 = code.getCode();
        if (code2==code3){
            Object user = redisTemplate.opsForHash().get("user", code.getPhone());
            Optional one =null;
            if (user!=null&&user.toString().equals("empty")){
                Example  example = Example.of(code.getPhone());
                 one = userRepository.findOne(example);
            }
            User o =null;
            if (one!=null){
                o = (User)one.get();
            }
            session.setAttribute(session.getId(),o);
            return "登陆成功！";
        }else{
            return "请核对验证码";
        }
    }

    @Override
    public String loginByPassword(User user) {
        try {
            // 先获取到Subject对象
            Subject subject = SecurityUtils.getSubject();
            // 创建UsernamePasswordToken对象，封装用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(),user.getPassWord());
            // 使用shiro框架进行校验
            subject.login(token);
            // 获取返回的结果
           if (subject.isAuthenticated()){
               Object uer = redisTemplate.opsForHash().get("user", user.getPhone());
               Optional one =null;
               if (user!=null&&user.toString().equals("empty")){
                   Example  example = Example.of(user.getPhone());
                   one = userRepository.findOne(example);
               }
               User o =null;
               if (one!=null){
                   o = (User)one.get();
               }

               session.setAttribute(session.getId(),o);
               return "success";
           }
        } catch (Exception e) {
            e.printStackTrace();
            return "用户名或者密码错误！";
        }
        return "";
       }

    @Override
    public Boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public Boolean updateUser(User user) {
        User user1 = userRepository.saveAndFlush(user);
        if (user1!=null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean addUser(User user) {
        User save = userRepository.save(user);
        if (save!=null){
            return true;
        }
        return false;
    }
}
