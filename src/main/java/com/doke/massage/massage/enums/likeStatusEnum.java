package com.doke.massage.massage.enums;

import lombok.Getter;

@Getter
public enum likeStatusEnum {
    LIKE(1,"点赞"),
    UNLIKE(0,"取消点赞/未点赞"),
    ;
    private Integer code;
    private String msg;
    likeStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

