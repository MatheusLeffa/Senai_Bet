package com.senai.userapi.controller;

import com.senai.userapi.entity.ResponseObject;
import com.senai.userapi.entity.User;
import com.senai.userapi.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(new ResponseObject(true, users));
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getUser(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok(new ResponseObject(true, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(new ResponseObject(true, createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PutMapping(value = "/users/update", produces = "application/json")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new ResponseObject(true, updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }
}
