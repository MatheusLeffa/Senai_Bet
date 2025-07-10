package com.senai.userapi.repository;

import com.senai.userapi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final static List<User> users = new ArrayList<>();
    private static Integer id = 0;

    public User createUser(User user) {
        user.setId(++id);
        users.add(user);
        return user;
    }

    public User updateUser(User user) {
        users.removeIf(u -> u.getId().equals(user.getId()));
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
