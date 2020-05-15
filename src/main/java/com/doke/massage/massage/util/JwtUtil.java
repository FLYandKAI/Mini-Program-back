package com.doke.massage.massage.util;

import com.doke.massage.massage.constant.Constant;
import com.doke.massage.massage.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JwtUtil {

    /**    iat = 创建时间戳     **/
    /**
     * 加密并获取token
     *
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {

        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
        date=calendar.getTime();
        String token = Jwts.builder().setSubject(Constant.SUBJECT)
                .claim("id", user.getId())
                .setIssuedAt(new Date())   //创建时间
                .setExpiration(date)       // 过期时间new Date(System.currentTimeMillis() + Constant.EXPIRE)
                .signWith(SignatureAlgorithm.HS256, Constant.KEY).compact();
        ;
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return 是否为null 以判断token时候有效
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
        /**
         *
         */
//    public static void main(String[] args) {
//        User user = new User();
//        user.setId(12);
//        JwtUtil jwtUtil = new JwtUtil();
//        String token = jwtUtil.geneJsonWebToken(user);
//        System.out.println(token);
//        String token1 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXBhbmdlciIsImlkI…1OTF9.ejw95foUx1-WFfEvngz0K-4KlxHcUbxJQwWgcYgSzrE";
//        System.out.println(jwtUtil.checkJWT(token));
//    }
}