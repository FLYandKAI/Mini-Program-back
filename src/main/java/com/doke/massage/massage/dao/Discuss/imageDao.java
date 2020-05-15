package com.doke.massage.massage.dao.Discuss;

import com.doke.massage.massage.pojo.Discuss.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface imageDao extends JpaRepository<Image,Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from Image where invitation_id = ?1",nativeQuery = true)
    void removeByInvitation_id(Integer invitation_id);
    @Query(value = "select Image.imageUrl from Image where invitation_id = ?1",nativeQuery = true)
    public List<String> getByInvitation_Id(Integer id);
    @Modifying
    @Query(value = "update Image set invitation_id = ?1 where id = ?2")
    public int setInvitationId(Integer invitation_id,Integer id);
}
