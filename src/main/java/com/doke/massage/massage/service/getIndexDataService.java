package com.doke.massage.massage.service;

import com.doke.massage.massage.dao.Index.RecommendDao;
import com.doke.massage.massage.dao.Index.SwiperDao;
import com.doke.massage.massage.pojo.Index.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页服务层
 */
@Service
public class getIndexDataService {
    @Autowired
    private SwiperDao swiperDao;   //注入轮播图dao

    @Autowired
    private RecommendDao recommendDao;//注入推荐的dao

    /**
     * 获取轮播图的Url
     * @return 轮播图地址的数组
     */
    public List<String> getSwiperImageUrl(){
        List<String> imageUrl = swiperDao.getImageUrl();
        return imageUrl;
    }

    /**
     * 获取首页推荐的简介（图片Url，标题）
     * @return
     */
    public List<Recommend> getRecommend(){
        List<Recommend> recommends = recommendDao.findAll();
        return recommends;
    }
}
