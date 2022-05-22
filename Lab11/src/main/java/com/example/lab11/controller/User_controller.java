package com.example.lab11.controller;

import com.example.lab11.entity.User_entity;
import com.example.lab11.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class User_controller {
   private final User_service userService;
@Autowired
    public User_controller(User_service userService) {
        this.userService = userService;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<User_entity> getUserList() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public User_entity createUser(@RequestBody User_entity user) {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public User_entity putUserName(@PathVariable long id, @RequestParam String name) {
        return userService.changeName(id, name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
