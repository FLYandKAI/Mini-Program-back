package com.doke.massage.massage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public  static String MD5(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[]byteDigest = md5.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for(int offset = 0; offset <byteDigest.length;offset ++)
            {
                i = byteDigest[offset];
                if(i<0)
                    i+=256;
                if(i<16)
                    stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i));
            }
            //32位加密
            return stringBuffer.toString();
            //16位加密
            /*return stringBuffer.toString().substring(8,24);*/
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    //工具类测试方法
    public static void main(String[] args) {
        System.out.println(MD5("123456"));
    }
}
