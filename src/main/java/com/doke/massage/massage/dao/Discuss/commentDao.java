package com.doke.massage.massage.dao.Discuss;

import com.doke.massage.massage.pojo.Discuss.Comment;
import com.doke.massage.massage.pojo.V.V_comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface commentDao extends JpaRepository<Comment,Integer> {
    @Query("select new com.doke.massage.massage.pojo.V.V_comment(u.userName,u.avatarUrl,c.content,c.createTime) from Comment c,User u  where c.invitation_id=?1 and c.user_id=u.id")
    public List<V_comment> findByInvitation_id(Integer id);
}
