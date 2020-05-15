package com.doke.massage.massage.dao.Index;

import com.doke.massage.massage.pojo.Index.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendDao extends JpaRepository<Recommend,Integer> {
    public List<Recommend> findAll();
}
