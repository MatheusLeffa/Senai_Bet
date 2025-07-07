package com.senai.userapi.service;

import com.senai.userapi.entity.User;
import com.senai.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws IllegalArgumentException {
        if (user.getAge() < 18) {
            throw new IllegalArgumentException("User must be 18 or older to bet.");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be empty.");
        }
        return userRepository.createUser(user);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(Integer id) throws Exception {
        User user = userRepository.getUser(id);
        if (user == null) {
            throw new Exception("User not found.");
        }
        return user;
    }

    public boolean isAllowedToBet(Integer id) throws Exception {
        if (userRepository.isAllowedToBet(id)) {
            return true;
        } else {
            throw new Exception("User is not allowed to bet.");
        }
    }


}
