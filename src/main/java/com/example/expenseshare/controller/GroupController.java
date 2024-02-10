package com.example.expenseshare.controller;

import com.example.expenseshare.model.Group;
import com.example.expenseshare.repository.UserRepository;
import com.example.expenseshare.service.OweRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.expenseshare.service.GroupService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createGroup")
    public String createGroup(@RequestBody Group groupRequest){
        String groupName = groupRequest.getGroupName();
        return groupService.createGroup(groupName);
    }

    @PostMapping("/addUserToGroup")
    public String addUserToGroup(@RequestBody AddUserToGroupRequest request){
        String userEmail = request.getUserEmail();
        String groupName = request.getGroupName();
        return groupService.addUserToGroup(userEmail, groupName);

    }

    @GetMapping("/getGroup")
    public List<String> getGroup(@RequestParam String groupName){
        return groupService.getGroup(groupName);
    }

    @GetMapping("/getGroupExpensesList")
    public ResponseEntity<List<String>> getGroupExpensesList(@RequestParam String groupName) {
        List<String> groupExpenses = groupService.getGroupExpensesList(groupName);

        if (groupExpenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(groupExpenses, HttpStatus.OK);
        }
    }

    @GetMapping("/getGroupTotal")
    public ResponseEntity<BigDecimal> getGroupTotal(@RequestParam String groupName) {
        BigDecimal groupTotal = groupService.getGroupTotal(groupName);

        if (groupTotal != null) {
            return new ResponseEntity<>(groupTotal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}



