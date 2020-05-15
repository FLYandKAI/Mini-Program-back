package com.doke.massage.massage.dao.Massage;

import com.doke.massage.massage.pojo.Massage.Acupoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.From;
import java.util.List;

public interface AcupointDao extends JpaRepository<Acupoint,Integer> {
    @Query(value = "select * from Acupoint where Acupoint.bodyPart_id = ?1",nativeQuery = true)
    public List<Acupoint> findAcupoint(Integer bodyPartId);
}
