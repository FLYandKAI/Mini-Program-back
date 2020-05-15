package com.doke.massage.massage.pojo.Massage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;
@Entity
public class massageMethod {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;   //手法
    private String method; //详细按摩方法
    private String attention; //注意事项
    private String imageUrl;   //图片

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public massageMethod() {
    }

    public massageMethod(String name, String method, String attention, String imageUrl) {
        this.name = name;
        this.method = method;
        this.attention = attention;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "massageMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", attention='" + attention + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
