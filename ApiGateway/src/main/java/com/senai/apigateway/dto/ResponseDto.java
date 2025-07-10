package com.senai.apigateway.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private boolean isSuccess;
    private Object result;

    public ResponseDto() {
    }

    public ResponseDto(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

}
