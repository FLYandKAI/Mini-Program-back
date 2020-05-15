package com.doke.massage.massage.controller;

import com.doke.massage.massage.pojo.Index.Recommend;
import com.doke.massage.massage.service.getIndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/index")
public class getIndexDataController {
    @Autowired
    private getIndexDataService getIndexDataService;//注入首页服务层

    /**
     * 获取首页轮播图的Url
     * @return Url的数组
     */
    @GetMapping("/getSwiper")
    public List<String> getSwiperImageUrl(){
        List<String> imageUrl = getIndexDataService.getSwiperImageUrl();
        return imageUrl;
    }

    /**
     *
     * 获取推荐
     * @return
     */
    @GetMapping("/getRecommend")
    public List<Recommend> getRecommend(){
        List<Recommend> recommends = getIndexDataService.getRecommend();
        return recommends;
    }
}
