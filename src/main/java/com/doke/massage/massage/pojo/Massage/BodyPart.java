package com.doke.massage.massage.pojo.Massage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BodyPart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String bobyPart;
    private String imagesUrl;
    @JsonIgnore()
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "bodyPart")
    private List<Acupoint> acupoints;
}
