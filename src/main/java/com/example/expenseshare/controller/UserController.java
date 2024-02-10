package com.example.expenseshare.controller;


import com.example.expenseshare.model.User;
import com.example.expenseshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<List<String>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<List<String>> userDTOs = new ArrayList<>();
        for(User user: users){
            List<String> userDTO = new ArrayList<>();
            userDTO.add(user.getName());
            userDTO.add(user.getEmail());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User userRequest){
        return userService.addUser(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
    }

    @GetMapping("/getUserGroupBalance")
    public Map<String, Object> getUserGroupBalance(@RequestParam String userEmail, @RequestParam String groupName){
        return userService.getUserGroupBalances(userEmail, groupName);
    }

}
