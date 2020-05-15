package com.doke.massage.massage.pojo.Discuss;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class likeCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer invitation_id;
    private Integer count;
    public likeCount(Integer invitation_id,Integer count){
        this.count = count;
        this.invitation_id=invitation_id;
    }
}
