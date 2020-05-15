package com.doke.massage.massage.pojo.Massage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Acupoint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String acupoint; //穴位名称
    private String location; //部位
    private String effect;   //贡献
    private String mode;
    private String imageUrl;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    private BodyPart bodyPart;
}
