package com.doke.massage.massage.pojo.Discuss;

import com.doke.massage.massage.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "invitation_id")
    private Integer invitation_id;
    @JoinColumn(name = "user_id")
    private Integer user_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
