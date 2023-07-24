package com.codingquokka.bottle.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FcmTokenVO {
    private String userID;
    private String token;
    private int isUsing;
    private String lastSending;
}
