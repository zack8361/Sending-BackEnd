package com.codingquokka.bottle.vo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserVO {
    private String USER_ID;
    private String USER_PW;
    private String IS_CERTIFIED;
    private int CNT_RECEIVED;
    private String JOIN_DT;
    private String RESTRICTION_DT;
    private String BELONG;
    private int IS_USING;
}
