package com.doke.massage.massage.pojo.Index;

import javax.persistence.*;

//轮播图
@Entity
public class Swiper {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;          //图片id
    private String imagesUrl;    //轮播图图片地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public Swiper(Integer id, String imagesUrl) {
        this.id = id;
        this.imagesUrl = imagesUrl;
    }
    public Swiper(){

    }
}
