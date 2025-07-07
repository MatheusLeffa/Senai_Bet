package com.senai.userapi.repository;

import com.senai.userapi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();
    private static Integer id = 0;

    public User createUser(User user) {
        user.setId(++id);
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean isAllowedToBet(Integer id) {
        User user = getUser(id);
        return user != null && user.getAge() >= 18;
    }
}
