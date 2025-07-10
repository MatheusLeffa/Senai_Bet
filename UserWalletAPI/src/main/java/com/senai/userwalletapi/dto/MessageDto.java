package com.senai.userwalletapi.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String event;
    private Object data;

    public MessageDto() {
    }

    public MessageDto(String event, Object data) {
        this.event = event;
        this.data = data;
    }
}
