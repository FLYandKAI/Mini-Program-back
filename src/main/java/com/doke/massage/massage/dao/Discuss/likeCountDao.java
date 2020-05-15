package com.doke.massage.massage.dao.Discuss;

import com.doke.massage.massage.pojo.Discuss.likeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface likeCountDao extends JpaRepository<likeCount,Integer> {
    @Query("from likeCount  where invitation_id=?1")
    public likeCount findByInvitation_id(Integer invitation_id);
}
