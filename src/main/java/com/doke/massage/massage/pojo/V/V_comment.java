package com.doke.massage.massage.pojo.V;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
@NoArgsConstructor
public class V_comment {
    private String username;
    private String avatarUrl;
    private String content;   //评论内容
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;  //创建时间
    private String returnTime;

    public V_comment(String username, String avatarUrl, String content, Date createTime) {
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.content = content;
        this.createTime = createTime;
    }
}
