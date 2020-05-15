package com.doke.massage.massage.dao.Discuss;

import com.doke.massage.massage.pojo.Discuss.Favorite;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface favoriteDao extends JpaRepository<Favorite,Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from Favorite where invitation_id=?1 and user_id = ?2")
    void deleteByInvitation_idAndUser_id(Integer id,Integer user_id);
    @Modifying
    @Query("select invitation_id from Favorite where user_id=?1  order by createTime desc ")
    public List<Integer> findByUser_id(Integer user_id);
    @Query("from Favorite where invitation_id=?1  and user_id=?2")
    public List<Favorite> findByInvitation_idAndUser_id(Integer invitation_id,Integer user_id);
}
