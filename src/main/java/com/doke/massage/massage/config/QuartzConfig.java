package com.doke.massage.massage.config;

import com.doke.massage.massage.task.likeStarTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;

public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(likeStarTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(300)  //设置时间周期单位秒
//                .withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
