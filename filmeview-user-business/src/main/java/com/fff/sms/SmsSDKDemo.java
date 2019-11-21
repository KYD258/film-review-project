package com.fff.sms;


import com.fff.dao.CodeRepository;
import com.fff.domain.Code;
import com.fff.sms.yun.SmsSingleSender;
import com.fff.sms.yun.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Component
public class SmsSDKDemo {
    @Value("${accesskey}")
    private String accesskey;
    @Value("${secretkey}")
    private String secretkey;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CodeRepository codeRepository;

	public void sendCode(String phoneNumber){
    	try {
    		//请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用
    		//手机号码
    		 //初始化单发
	    	SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
	    	SmsSingleSenderResult singleSenderResult;
	    	Random random=new Random();
            int i = random.nextInt(9999);
            //存redis
            Code code=new Code();
            code.setCode(i);
            code.setPhone(phoneNumber);
            redisTemplate.opsForValue().set("code",code);
            redisTemplate.expire("code",3000,TimeUnit.SECONDS);
            //添加定时器，3分钟后过期！！！
//            Timer timer=new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    save.setCode(null);
//                    codeRepository.saveAndFlush(save);
//
//                }
//            },600000);
            //普通单发,注意前面必须为【】符号包含，置于头或者尾部。
	    singleSenderResult = singleSender.send(0, "86", phoneNumber, "【靓仔科技】尊敬的用户：您的验证码："+i+"，工作人员不会索取，请勿泄漏。", "", "");
	    	System.out.println(singleSenderResult);
	    	
	    	
	    	//语音验证码发送
    		//SmsVoiceVerifyCodeSender smsVoiceVerifyCodeSender = new SmsVoiceVerifyCodeSender(accesskey,secretkey);
    		//SmsVoiceVerifyCodeSenderResult smsVoiceVerifyCodeSenderResult = smsVoiceVerifyCodeSender.send("86",phoneNumber, "444144",2,"");
    		//System.out.println(smsVoiceVerifyCodeSenderResult);

    	} catch (Exception e) {
			e.printStackTrace();
		}
    }


}
