package com.senai.apigateway.integration;

import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "UserApi", url = "http://localhost:8082")
public interface UserApi {

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> getUsers();

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getUser(@PathVariable Integer id);

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user);

    @PutMapping(value = "/users/update", produces = "application/json")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User user);
}
