package com.doke.massage.massage.constant;

public class Constant {
    //
    public static final String SUBJECT = "dapanger";

    //过期时长  1000毫秒 * 60秒 * 60分钟 * 24小时 * 7天
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 8;//7天

    //秘钥
    public static final String KEY = "66666666";

    //图片保存路径
    public static final String imageBasePath = "/images/InvitationImage";
//    public static final String imageBasePath = "E:\\web_workspace\\massage\\image";
    //获取openid的url
    //sk
    public static final String openidUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=wx841aed5ef4e9ad7c&secret=53275f05eef6e7934706c32c63e648f7&js_code=";
    //doke
//      public static final String openidUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=wx6ba2c2c16ea8adb9&secret=a4c640075281f96d463363bd5777d838&js_code=";

}

