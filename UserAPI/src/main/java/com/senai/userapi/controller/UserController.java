package com.senai.userapi.controller;

import com.senai.userapi.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {

    }

    @GetMapping("/users/isAllowedToBet/{id}")
    public ResponseEntity<Boolean> isAllowedToBet(@PathVariable Integer id) {

    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {

    }
}
