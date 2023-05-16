package com.codingquokka.bottle.vo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserVO {
    private String userId;
    private String userPw;
    private String isCertified;
    private int cntReceived;
    private String joinDt;
    private String restrictionDt;
    private String belong;
    private String uuid;
    private int isUsing;
}
