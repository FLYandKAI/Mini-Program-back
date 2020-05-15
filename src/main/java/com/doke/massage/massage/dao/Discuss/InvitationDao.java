package com.doke.massage.massage.dao.Discuss;

import com.doke.massage.massage.pojo.Discuss.Invitation;
import com.doke.massage.massage.pojo.Discuss.InvitationAndUser;
import com.doke.massage.massage.pojo.User;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface InvitationDao extends JpaRepository<Invitation,Integer>, JpaSpecificationExecutor {
    @Transactional
    @Modifying
    @Query(value = "delete from Invitation where user_id=?1 and id = ?2",nativeQuery = true)
    void removeByUser_IdAndId(Integer user_id,Integer id);
    public List<Invitation> findByUser_Id(Integer user_id);
    @Query(value = "select new com.doke.massage.massage.pojo.Discuss.InvitationAndUser(i.id,u.userName,u.avatarUrl,i.title,i.content,i.imageUrl,i.createTime,i.returnTime) "
            + "from Invitation i ,User u where i.user = u.id")
    public List<InvitationAndUser> find(Pageable pageable);
    @Modifying
    @Query("update Invitation i set i.imageUrl = ?1 where i.id = ?2")
    public int setImageUrl(String imageUrl,Integer id);
    @Modifying
    @Query("update Invitation set user_id=?2 where id = ?1")
    public int setUserId(Integer id,Integer user_id);
    @Query("from Invitation where id in (?1) order by createTime desc")
    public List<Invitation> findById(List<Integer> id);
}
