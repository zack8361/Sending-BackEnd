package com.codingquokka.bottle.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class UserVO {
    private String email;
    private String password;
    private int certified;
    private int cntReceived;
    private int cntSent;
    private String joinDt;
    private int dDay;
    private String restrictionDt;
    private String belong;
    private String uuid;
    private int isUsing;
    private String lastRequest;
}
