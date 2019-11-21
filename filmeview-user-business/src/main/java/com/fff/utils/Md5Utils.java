package com.fff.utils;

/**
 * Created by 54110 on 2019-09-17.
 */

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5加密
 * @author Administrator
 *
 */
public class Md5Utils {

    public static String getMd5Password(String pass){
        String hashAlgorithName = "MD5";//加密算法
        String password = pass;//登陆时的密码
        int hashIterations =1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("hello");//使用登录名做为salt
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        return simpleHash.toString();
    }
}
