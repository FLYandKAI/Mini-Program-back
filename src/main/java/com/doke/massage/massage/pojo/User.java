package com.doke.massage.massage.pojo;

import com.doke.massage.massage.pojo.Discuss.Favorite;
import com.doke.massage.massage.pojo.Discuss.Invitation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String avatarUrl;
    private String openid;
    @JsonIgnore
    @OneToMany(mappedBy = "user",targetEntity = Invitation.class)
    private List<Invitation> invitation;//贴子
}
