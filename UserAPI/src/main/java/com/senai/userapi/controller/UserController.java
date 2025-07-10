package com.senai.userapi.controller;

import com.senai.userapi.dto.ResponseDto;
import com.senai.userapi.entity.User;
import com.senai.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseDto> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(new ResponseDto(true, users));
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseDto> getUser(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok(new ResponseDto(true, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseDto> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(new ResponseDto(true, createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(false, e.getMessage()));
        }
    }

    @PutMapping(value = "/users/update", produces = "application/json")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new ResponseDto(true, updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(false, e.getMessage()));
        }
    }
}
