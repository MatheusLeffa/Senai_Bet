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
            throw new IllegalArgumentException("Usuário menor de idade.");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        return userRepository.createUser(user);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(Integer id) throws Exception {
        User user = userRepository.getUser(id);
        if (user == null) {
            throw new Exception("Usuario não encontrado.");
        }
        return user;
    }

    public User updateUser(User user) throws Exception {
        User userToUpdate = userRepository.getUser(user.getId());
        if (userToUpdate == null) {
            throw new Exception("Usuario nao encontrado.");
        }
        return userRepository.updateUser(user);
    }
}
