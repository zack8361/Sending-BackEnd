package com.codingquokka.bottle.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomVO {
    private String chatId;
    private String userId;
    private int isUsing;
}
