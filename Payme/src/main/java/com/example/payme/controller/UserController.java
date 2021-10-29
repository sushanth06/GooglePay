package com.example.payme.controller;

import java.util.List;

import com.example.payme.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payme.entity.User;
import com.example.payme.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/createUser")
    public User addUserParams(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String phone) {

        UserDto userDto = new UserDto(firstName, lastName, phone);
        return userService.addUser(userDto);
    }

    @GetMapping("/port")
    public String getOrderServicePortNo() {
        return userService.getBankServicePortNo();
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}