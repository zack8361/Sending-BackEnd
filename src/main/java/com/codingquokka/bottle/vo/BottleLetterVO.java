package com.codingquokka.bottle.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BottleLetterVO {
    private String letterId;
    private String title;
    private String content;
    private String senderId;
    private String receivedId;
    private String isRead;
    private String isReportedMsg;
    private String isContinued;
    private String sendDt;
    private String isUsing;
}
