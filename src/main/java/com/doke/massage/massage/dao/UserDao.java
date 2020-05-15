package com.doke.massage.massage.dao;

import com.doke.massage.massage.pojo.User;
import com.doke.massage.massage.pojo.V.V_like_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserDao extends JpaRepository<User,Integer> {
    //根据openid查找user
    public List<User> findByOpenid(String openid);
    @Query("select new com.doke.massage.massage.pojo.V.V_like_user(u.avatarUrl,u.userName) from User u where id in ?1")
    public List<V_like_user> findByIdIn(List<Integer> user_ids);
}
