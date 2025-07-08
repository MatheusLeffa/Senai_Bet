package com.senai.betapi.entity;

import lombok.Data;

@Data
public class ResponseObject {
    private boolean isSuccess;
    private Object result;

    public ResponseObject(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public ResponseObject() {
    }
}
