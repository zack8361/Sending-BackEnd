package com.codingquokka.bottle.vo;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserVO {
    private String email;
    private String password;
    private int certified;
    private String uuid;
    private int cntReceived;
    private int cntSent;
    private String joinDt;
    private int dDay;
    private String restrictionDt;
    private String belong;
    private int isUsing;
    private String lastRequest;
}
