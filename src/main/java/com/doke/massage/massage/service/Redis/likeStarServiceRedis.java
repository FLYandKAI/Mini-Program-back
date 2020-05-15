package com.doke.massage.massage.service.Redis;
import com.doke.massage.massage.enums.likeStatusEnum;
import com.doke.massage.massage.pojo.Discuss.UserLike;
import com.doke.massage.massage.pojo.Discuss.likeCount;
import com.doke.massage.massage.util.likeKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class likeStarServiceRedis{
    @Resource(name = "myRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    likeKeyUtil likeKeyUtil;

    /**
     * 保存用户点赞数据到Redis
     * @param likeUserId
     * @param beLikeId
     */
    public void saveLiked2Redis(String likeUserId,String beLikeId){
        String key =   likeKeyUtil.getLikeKey(likeUserId,beLikeId);
        redisTemplate.opsForHash().put(likeKeyUtil.LIKED_KEY,key,likeStatusEnum.LIKE.getCode());
    }
    public String getBy2id(String likeUserId,String beLikeId){
        String key = likeKeyUtil.getLikeKey(likeUserId, beLikeId);
        Object o = redisTemplate.opsForHash().get(likeKeyUtil.LIKED_KEY, key);
        if (o == null)
            return null;

        return o.toString();
    }
    /**
     * 取消点赞。将状态改变为0
     * @param likeUserId
     * @param beLikeId
     */
    public void unlikeFromRedis(String likeUserId,String beLikeId){
        String key =   likeKeyUtil.getLikeKey(likeUserId,beLikeId);
        redisTemplate.opsForHash().put(likeKeyUtil.LIKED_KEY,key,likeStatusEnum.UNLIKE.getCode());
    }

    /**
     * 从Redis中删除一条点赞数据
     * @param likeUserId
     * @param beLikeId
     */
    public void deleteLikedFromRedis(String likeUserId,String beLikeId){
        String key =   likeKeyUtil.getLikeKey(likeUserId,beLikeId);
        redisTemplate.opsForHash().delete(likeKeyUtil.LIKED_KEY,key);

    }

    /**
     * 贴子点赞数+1
     * @param beLikeId
     */
    public void incrementLikedCount(String beLikeId){
        redisTemplate.opsForHash().increment(likeKeyUtil.LIKED_KEY_COUNT,beLikeId,1);
    }

    /**
     * 贴子点赞数-1
     * @param beLikeId
     */
    public void decrementLikedCount(String beLikeId){
        redisTemplate.opsForHash().increment(likeKeyUtil.LIKED_KEY_COUNT,beLikeId,-1);
    }
    //根据用户id获取点赞数据
    public List<Integer> getLikedDataByLikeId(Integer LikeId){
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(likeKeyUtil.LIKED_KEY, ScanOptions.NONE);
        List<Integer> invitation_ids = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，beLikeId
            String[] split = key.split("::");
            if (LikeId == Integer.valueOf(split[0]))
                invitation_ids.add(Integer.valueOf(split[1]));
        }
        return invitation_ids;
    }
    //根据贴子id获取点赞数据
    public List<Integer> getLikedDataByBeLikeId(Integer beLikeId){
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(likeKeyUtil.LIKED_KEY, ScanOptions.NONE);
        List<Integer> user_ids = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，beLikeId
            String[] split = key.split("::");
            if (beLikeId == Integer.valueOf(split[1]))
                user_ids.add(Integer.valueOf(split[0]));
        }
        return user_ids;
    }
    /**
     * 获取所有点赞数据
     * @return
     */
    public List<UserLike> getLikedDataFromRedis(){
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(likeKeyUtil.LIKED_KEY, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedPostId
            String[] split = key.split("::");
            String likeUserId = split[0];
            String beLikeId = split[1];
            Integer value = (Integer) entry.getValue();
            System.out.println("key:"+key+"---value:"+value);
            //组装成 UserLike 对象
            UserLike userLike = new UserLike(Integer.parseInt(likeUserId), Integer.parseInt(beLikeId), value);
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(likeKeyUtil.LIKED_KEY, key);
        }

        return list;
    }
    //根据贴子id获取点赞数量
    public Integer getLikedCountByInvitationId(String invitation_id){
        System.out.println("RedisService"+invitation_id);
        Object likeNum = redisTemplate.opsForHash().get(likeKeyUtil.LIKED_KEY_COUNT, invitation_id);
        System.out.println("like 数量"+likeNum);
        if (likeNum==null)
            return 0;
        return Integer.valueOf(likeNum.toString());
    }

    /**
     * 获取贴子点赞数量
     * @return
     */
    public List<likeCount> getLikedCountFromRedis(){
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(likeKeyUtil.LIKED_KEY_COUNT, ScanOptions.NONE);
        List<likeCount> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 likeCount
            Integer key = Integer.parseInt(map.getKey().toString());
            System.out.println("key:"+key+"---value:"+map.getValue());
            likeCount dto = new likeCount(key, Integer.parseInt(map.getValue().toString()));
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(likeKeyUtil.LIKED_KEY_COUNT, key.toString());
        }
        return list;
    }
}
