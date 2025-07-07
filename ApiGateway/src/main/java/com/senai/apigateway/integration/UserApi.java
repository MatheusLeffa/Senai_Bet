package com.senai.apigateway.integration;

import com.senai.apigateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "BetApi", url = "http://localhost:8082")
public interface UserApi {

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers();

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id);

    @GetMapping("/users/isAllowedToBet/{id}")
    public ResponseEntity<Boolean> isAllowedToBet(@PathVariable Integer id);

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user);
}
