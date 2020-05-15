package com.doke.massage.massage.controller;

import com.alibaba.fastjson.JSONObject;
import com.doke.massage.massage.pojo.User;
import com.doke.massage.massage.service.UserService;
import com.doke.massage.massage.util.HttpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String code,
                        String avatarUrl,
                        String userName){
        System.out.println("code"+code);
        return userService.login(code,avatarUrl,userName);
    }
    /**
     * 验证用户token
     * @param token
     * @return 成功 ：user.id
     *         失败 ：""（空字符）
     */
    @PostMapping("/checkToken")
    public String checkToken(String token){
        return userService.checkToken(token);
    }
}
