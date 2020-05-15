package com.doke.massage.massage.pojo.Discuss;

import com.doke.massage.massage.pojo.Discuss.Image;
import com.doke.massage.massage.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;      //标题
    private String content;    //内容
    @JsonIgnore
    @OneToMany(targetEntity = Image.class,mappedBy = "invitation")
    private List<String> imageUrl1;   //图片
    private String imageUrl;
    private String video;      //视频
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;   //创建时间
    private String returnTime; //返回时间
    @JsonIgnore
    @ManyToOne
    private User user;//绑定user.id
}
