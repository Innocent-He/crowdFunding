package com.hgy.crowd.util;

import com.hgy.crowd.constant.CrowdConstant;
import com.hgy.crowd.exception.NoStringException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author He
 * @Date 2020/3/30 17:20
 * @Version 1.0
 * 判断当前请求是否为Ajax请求
 * 密码加密
 */

public class CrowdUtil {
    public static String md5(String source){
        if (source==null||source.length()==0){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            String algorithm = "md5";
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            byte[] digest = md5.digest(source.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest);
            String encode = bigInteger.toString(16).toUpperCase();
            return encode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //如果是Ajax请求则返回true
    public static boolean judgeRequestType(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        return (accept!=null&&accept.contains("application/json"));
    }
}
