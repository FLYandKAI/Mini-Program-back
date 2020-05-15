package com.doke.massage.massage;

import com.doke.massage.massage.dao.Discuss.InvitationDao;
import com.doke.massage.massage.dao.Discuss.commentDao;
import com.doke.massage.massage.dao.Discuss.favoriteDao;
import com.doke.massage.massage.pojo.Discuss.Comment;
import com.doke.massage.massage.pojo.Discuss.Favorite;
import com.doke.massage.massage.service.Redis.likeStarServiceRedis;
import com.doke.massage.massage.service.UserService;
import com.doke.massage.massage.service.discussService;
import com.doke.massage.massage.service.likeStarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@SpringBootTest
class MassageApplicationTests {
    @Autowired
    private likeStarService likeStarService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationDao invitationDao;
    @Autowired
    private discussService discussService;
    @Autowired
    private favoriteDao favoriteDao;
    @Test
    void contextLoads() {
        favoriteDao.deleteByInvitation_idAndUser_id(21,5);
    }
    @Test
    void checkToken(){
        System.out.println(userService.checkToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXBhbmdlciIsImlkIjo1LCJpYXQiOjE1ODkzNTkxODEsImV4cCI6MTU5MjAzNzU4MX0.od4_qaB2QvuXZVIj-Szd71PvujTu0K2aQV1n2PyRVMc"));
    }

}
