package com.doke.massage.massage.pojo.Discuss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
public class InvitationAndUser {
    private Integer id;
    private Integer likeCount;//点赞数量
    private String userName;
    private String avatarUrl;  //头像
    private String title;      //标题
    private String content;
    private String imageUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;   //创建时间
    private String returnTime;

    public InvitationAndUser(Integer id, String userName, String avatarUrl, String title, String content, String imageUrl, Date createTime, String returnTime){
        this.id=id;
        this.userName=userName;
        this.avatarUrl=avatarUrl;
        this.title=title;
        this.content=content;
        this.imageUrl=imageUrl;
        this.createTime=createTime;
        this.returnTime=returnTime;
    }
}
