package com.senai.apigateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.entity.User;
import com.senai.apigateway.integration.UserApi;
import com.senai.apigateway.integration.UserWalletApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserApi userApi;
    private final UserWalletApi userWalletApi;
    private final ObjectMapper objectMapper;

    public UserController(UserApi userApi, UserWalletApi userWalletApi) {
        this.userApi = userApi;
        this.userWalletApi = userWalletApi;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> getUsers() {
        return userApi.getUsers();
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userApi.getUser(id).getBody());
    }

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        ResponseObject createUserResponse = userApi.createUser(user).getBody();

        if (createUserResponse == null) {
            return ResponseEntity.status(500).body(new ResponseObject(false, "Erro ao deserializar objeto."));
        }

        User createdUser = objectMapper.convertValue(createUserResponse.getResult(), User.class);
        userWalletApi.createWallet(createdUser);
        return ResponseEntity.ok(new ResponseObject(true, createdUser));
    }
}
