package com.senai.userapi.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private boolean isSuccess;
    private Object result;

    public ResponseDto(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }
}
