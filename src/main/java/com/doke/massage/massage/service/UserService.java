package com.doke.massage.massage.service;

import com.alibaba.fastjson.JSONObject;
import com.doke.massage.massage.constant.Constant;
import com.doke.massage.massage.dao.UserDao;
import com.doke.massage.massage.pojo.User;
import com.doke.massage.massage.util.HttpsUtil;
import com.doke.massage.massage.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpsUtil httpsUtil;
    JwtUtil jwtUtil = new JwtUtil();//token工具栏类

    /**
     * 验证用户token是否过期
     * if 未过期 return user.id;
     * else return "";
     * @param token
     * @return
     */
    public String checkToken(String token){
        Claims claims = jwtUtil.checkJWT(token);
        if(claims != null)
        {
            return "" + claims.get("id");
        }
        return "";
    }

    /**
     * 根据用户发过来的code获取openid，若已存在这个openid，
     * 则把用户的id加入token发回前端，
     * 若不存在，则将该用户存入数据库并生成token
     * @param code
     * @param avatarUrl
     * @param userName
     * @return
     */
    public String login(String code,String avatarUrl,String userName){
        String url = Constant.openidUrl+code+"&grant_type=authorization_code";
        JSONObject get = httpsUtil.httpRequest(url, "GET", "UTF-8");
        String openid = (String) get.get("openid");
        System.out.println("get"+get);
        System.out.println("openid="+openid);
        if (openid==null)
        return "code已使用";
        List<User> user = userDao.findByOpenid(openid);
        //若该用户已存在
        if(user!=null){
            String token = jwtUtil.geneJsonWebToken(user.get(0));
            System.out.println(token);
            return token;
        }
        //若用户不存在
        User user1 = new User();
        user1.setOpenid(openid);
        user1.setAvatarUrl(avatarUrl);
        user1.setUserName(userName);
        User save = userDao.save(user1);
        String token = jwtUtil.geneJsonWebToken(save);
        return token;
    }
}
