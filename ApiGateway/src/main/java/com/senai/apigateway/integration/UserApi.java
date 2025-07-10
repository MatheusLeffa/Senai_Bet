package com.senai.apigateway.integration;

import com.senai.apigateway.dto.ResponseDto;
import com.senai.apigateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "UserApi", url = "http://localhost:8082")
public interface UserApi {

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseDto> getUsers();

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseDto> getUser(@PathVariable Integer id);

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseDto> createUser(@RequestBody User user);
}
