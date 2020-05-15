package com.doke.massage.massage.pojo.Discuss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;//用户id
    private String content;   //评论内容
    @JoinColumn(name = "invitation_id")
    private Integer invitation_id;//贴子id
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;  //创建时间

}
