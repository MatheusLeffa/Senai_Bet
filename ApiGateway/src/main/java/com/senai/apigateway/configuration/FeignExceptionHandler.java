package com.senai.apigateway.configuration;

import com.senai.apigateway.dtos.ResponseObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeignExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException e) {
        try {
            String responseBody = e.contentUTF8();
            ResponseObject responseObject = objectMapper.readValue(responseBody, ResponseObject.class);
            return ResponseEntity.status(e.status()).body(responseObject);
        } catch (Exception parseException) {
            // Fallback para erro genérico se falhar o parsing
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseObject(false, "Erro ao comunicar com serviço."));
        }
    }
}
