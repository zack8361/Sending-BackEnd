package com.codingquokka.bottle.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChatContentVO {
    private String chatId;
    private String userId;
    private String content;
    private String sendDt;
    private String isUsing;
}
