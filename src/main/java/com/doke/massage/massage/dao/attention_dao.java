package com.doke.massage.massage.dao;

import com.doke.massage.massage.pojo.Attention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface attention_dao extends JpaRepository<Attention,Integer> {
    @Query(value = "select Attention.Attention from Attention",nativeQuery = true)
    public String query();
}
