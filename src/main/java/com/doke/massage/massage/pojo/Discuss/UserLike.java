package com.doke.massage.massage.pojo.Discuss;
import com.doke.massage.massage.enums.likeStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //被点赞的贴子id
    private Integer invitation_id;
    //点赞的用户id
    private Integer user_id;
    //点赞的状态。默认未点赞
    private Integer status = likeStatusEnum.UNLIKE.getCode();
    public UserLike(Integer user_id, Integer invitation_id, Integer status) {
        this.invitation_id = invitation_id;
        this.user_id = user_id;
        this.status = status;
    }
}
