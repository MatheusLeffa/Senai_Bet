package com.senai.apigateway.dtos;

import lombok.Data;

@Data
public class MessageObject {
    private String event;
    private Object data;

    public MessageObject() {
    }

    public MessageObject(String event, Object data) {
        this.event = event;
        this.data = data;
    }
}
