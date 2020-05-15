package com.doke.massage.massage.service;

import com.alibaba.fastjson.JSONObject;
import com.doke.massage.massage.dao.Discuss.InvitationDao;
import com.doke.massage.massage.dao.Discuss.likeCountDao;
import com.doke.massage.massage.dao.Discuss.userLikeDao;
import com.doke.massage.massage.dao.UserDao;
import com.doke.massage.massage.pojo.Discuss.UserLike;
import com.doke.massage.massage.pojo.Discuss.likeCount;
import com.doke.massage.massage.pojo.V.V_like_user;
import com.doke.massage.massage.service.Redis.likeStarServiceRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class likeStarService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private userLikeDao userLikeDao;
    @Autowired
    private likeCountDao likeCountDao;
    @Autowired
    private likeStarServiceRedis likeStarServiceRedis;
    @Autowired
    private InvitationDao invitationDao;
    //获取贴子点赞数量
    public Integer getLikedCountByInvitationId(String invitation_id){
        Integer likeNum = likeStarServiceRedis.getLikedCountByInvitationId(invitation_id);
        likeCount lc = likeCountDao.findByInvitation_id(Integer.valueOf(invitation_id));
        //点赞数量属于无关紧要的操作，出错无需抛异常
        if (lc != null){
            likeNum += lc.getCount();
        }
        return likeNum;
    }
    //根据用户id获取点赞数据
    public Object getMyLikedList(String token){
        String s = userService.checkToken(token);
        JSONObject object = new JSONObject();
        if (s == "") {
            object.put("list", -1);
            return object;
        }else {
            List<Integer> invitation_ids = likeStarServiceRedis.getLikedDataByLikeId(Integer.valueOf(s));
            invitation_ids.addAll(userLikeDao.findByUser_id(Integer.valueOf(s)));
            object.put("list", invitationDao.findById(invitation_ids));
            return object;}
    }

    //获取贴子的点赞数据
    public List<V_like_user> getLiked(String invitation_id){
        List<Integer> user_ids = userLikeDao.findByInvitation_id(Integer.valueOf(invitation_id));
        user_ids.addAll(likeStarServiceRedis.getLikedDataByBeLikeId(Integer.valueOf(invitation_id)));
        return userDao.findByIdIn(user_ids);
    }
    //新增点赞
    public String saveLiked(String token,String beLikeId){
        String s = userService.checkToken(token);
        if (s=="")
            return "-1";
        String status = likeStarServiceRedis.getBy2id(s, beLikeId);
        if (status == null){
            likeStarServiceRedis.saveLiked2Redis(s,beLikeId);
            likeStarServiceRedis.incrementLikedCount(beLikeId);
            return "1";
        }
        if(status.equals("1")){
            likeStarServiceRedis.unlikeFromRedis(s,beLikeId);
            likeStarServiceRedis.decrementLikedCount(beLikeId);
            return "0";
        }
        else {
            likeStarServiceRedis.saveLiked2Redis(s,beLikeId);
            likeStarServiceRedis.incrementLikedCount(beLikeId);
            return "1";
        }
    }
    public void transLikedFromRedis2DB() {
        List<UserLike> list = likeStarServiceRedis.getLikedDataFromRedis();
        for (UserLike like : list) {
            UserLike ul = userLikeDao.findByInvitation_idAndUser_id(like.getInvitation_id(), like.getUser_id());
            if (ul == null) {
                //没有记录，直接存入
                userLikeDao.save(like);
            } else {
                //有记录，需要更新
                if (like.getStatus()==1){
                    if (ul.getStatus()==0){
                    ul.setStatus(1);
                    userLikeDao.save(ul);
                    }
                    else {
                    ul.setStatus(0);
                    userLikeDao.save(ul);
                    }
                }
            }
        }
    }
    public void transLikedCountFromRedis2DB(){
        List<likeCount> list = likeStarServiceRedis.getLikedCountFromRedis();
        for (likeCount dto : list) {
            likeCount lc = likeCountDao.findByInvitation_id(dto.getInvitation_id());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (lc != null){
                Integer likeNum = lc.getCount() + dto.getCount();
                lc.setCount(likeNum);
            }
            //更新点赞数量
            likeCountDao.save(lc);
        }
    }
}
