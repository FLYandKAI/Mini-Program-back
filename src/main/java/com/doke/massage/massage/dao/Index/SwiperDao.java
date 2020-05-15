package com.doke.massage.massage.dao.Index;

import com.doke.massage.massage.pojo.Index.Swiper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SwiperDao extends JpaRepository<Swiper,Integer> {
    @Query(value = "select swiper.imagesUrl from swiper" ,nativeQuery = true)
    public List<String> getImageUrl();
}
