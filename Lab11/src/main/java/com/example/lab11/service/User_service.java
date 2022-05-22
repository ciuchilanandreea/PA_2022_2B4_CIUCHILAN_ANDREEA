package com.example.lab11.service;

import com.example.lab11.entity.User_entity;
import com.example.lab11.repository.User_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_service {
    private final User_repository userRepository;
@Autowired
    public User_service(User_repository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User_entity> getAllUsers() {
        return userRepository.findAll();
    }

    public User_entity addUser(User_entity user) {
        userRepository.save(user);
        return user;
    }

    public User_entity changeName(long id, String nume) {
        User_entity user = userRepository.getUserByIdUser(id);
        user.setName(nume);
        userRepository.save(user);
        return user;
    }

    public void deleteUser(long id) {
        User_entity user = userRepository.getUserByIdUser(id);
        userRepository.deleteUserByIdUser(user.getIdUser());
    }
}
