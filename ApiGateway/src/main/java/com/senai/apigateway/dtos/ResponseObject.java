package com.senai.apigateway.dtos;

import lombok.Data;

@Data
public class ResponseObject {
    private boolean isSuccess;
    private Object result;

    public ResponseObject() {
    }

    public ResponseObject(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

}
