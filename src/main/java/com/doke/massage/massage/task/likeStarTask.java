package com.doke.massage.massage.task;

import com.doke.massage.massage.service.likeStarService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class likeStarTask extends QuartzJobBean {
    @Autowired
    likeStarService likeStarService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("LikeStarTask-------- "+sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        likeStarService.transLikedFromRedis2DB();
        likeStarService.transLikedCountFromRedis2DB();
    }
}
