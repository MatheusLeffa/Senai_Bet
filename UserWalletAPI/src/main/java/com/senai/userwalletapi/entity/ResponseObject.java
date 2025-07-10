package com.senai.userwalletapi.entity;

import lombok.Data;

@Data
public class ResponseObject {
    private boolean isSuccess;
    private Object result;

    public ResponseObject(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }
}
