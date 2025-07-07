package com.senai.apigateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.entity.User;
import com.senai.apigateway.integration.UserApi;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserApi userApi;

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> getUsers() {
        return userApi.getUsers();
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userApi.getUser(id).getBody());
    }

    @GetMapping(value = "/users/isAllowedToBet/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> isAllowedToBet(@PathVariable Integer id) {
        return ResponseEntity.ok(userApi.isAllowedToBet(id).getBody());
    }

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userApi.createUser(user).getBody());
    }
}
