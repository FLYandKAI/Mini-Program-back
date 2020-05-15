package com.doke.massage.massage.dao.Massage;

import com.doke.massage.massage.pojo.Massage.massageMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface massageMethodDao extends JpaRepository<massageMethod,Integer> {
    @Query(value = "select * from massageMethod",nativeQuery = true)
    public List<massageMethod> getMethod();
}
