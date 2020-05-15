package com.doke.massage.massage.dao.Discuss;

import com.doke.massage.massage.pojo.Discuss.UserLike;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface userLikeDao extends JpaRepository<UserLike,Integer> {
    //根据贴子id查询贴子列表（谁给这篇贴子点过赞）
    @Query("from UserLike where invitation_id=?1")
    List<UserLike> getUserLikesByInvitation_id(Integer invitation_id);
    @Query(value = "select UserLike .invitation_id from UserLike where user_id = ?1 and status =1",nativeQuery = true)
    List<Integer> findByUser_id(Integer user_id);
    @Query("from UserLike where invitation_id=?1 and user_id=?2")
    UserLike findByInvitation_idAndUser_id(Integer invitation_id,Integer user_id);
    @Query(value = "select UserLike.user_id from UserLike where invitation_id=?1 and status = 1",nativeQuery = true)
    List<Integer> findByInvitation_id(Integer invitation_id);
}
