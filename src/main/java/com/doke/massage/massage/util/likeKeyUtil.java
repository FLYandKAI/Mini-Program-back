package com.doke.massage.massage.util;

import org.springframework.stereotype.Component;

@Component
public class likeKeyUtil {

    //保存点赞数据的key
    public static final String LIKED_KEY = "LIKED_KEY";
    //保存贴子被点赞数量的key
    public static final String LIKED_KEY_COUNT = "LIKED_COUNT";

    /**
     * 拼接点赞用户和被点赞贴子id 格式：12::51
     * @param likeUserId
     * @param beLikeId
     * @return
     */
    public static String getLikeKey(String likeUserId,String beLikeId){
        StringBuilder builder = new StringBuilder();
        builder.append(likeUserId);
        builder.append("::");
        builder.append(beLikeId);
        return builder.toString();
    }
}
