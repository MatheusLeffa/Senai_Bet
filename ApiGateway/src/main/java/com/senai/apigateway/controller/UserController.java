package com.senai.apigateway.controller;

import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.entity.User;
import com.senai.apigateway.integration.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userApi.createUser(user).getBody());
    }

    @PutMapping(value = "/users/update", produces = "application/json")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userApi.updateUser(user).getBody());
    }
}
