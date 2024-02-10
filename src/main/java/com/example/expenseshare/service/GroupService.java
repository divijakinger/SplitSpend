package com.example.expenseshare.service;

import com.example.expenseshare.model.*;
import com.example.expenseshare.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseShareRepository expenseShareRepository;
    public String createGroup(String groupName) {
        if (groupName != null) {
            groupRepository.save(new Group(groupName));
            return "Group Created";
        } else {
            return "Group Name cannot be null";
        }
    }

    public String addUserToGroup(String userEmail, String groupName) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        Optional<Group> groupOptional = groupRepository.findByGroupName(groupName);

        if (userOptional.isPresent() && groupOptional.isPresent()) {
            User user = userOptional.get();
            Group group = groupOptional.get();

            if (!userGroupRepository.existsByUser_IdAndGroup_GroupId(user.getId(), group.getGroupId())) {
                UserGroup userGroup = new UserGroup();
                UserGroupId userGroupId = new UserGroupId();
                userGroupId.setUser(user);
                userGroupId.setGroup(group);
                userGroup.setId(userGroupId);

                userGroupRepository.save(userGroup);

                return "User added to group successfully";
            } else {
                return "User is already in the group";
            }
        } else {
            return "User or group not found";
        }
    }

    public List<String> getGroup(String groupName) {
        Optional<Group> groupOptional = groupRepository.findByGroupName(groupName);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            Long groupId = group.getGroupId();

            List<UserGroup> userGroups = userGroupRepository.findByGroupId(groupId);

            System.out.println(userGroups);

            List<Long> userIds = userGroups.stream()
                    .map(UserGroup::getUser)
                    .map(User::getId)
                    .collect(Collectors.toList());

            List<User> users = userRepository.findAllById(userIds);

            List<String> userNameList = users.stream()
                    .map(User::getName)
                    .collect(Collectors.toList());

            return userNameList;

        } else {
            return null;
        }
    }

    public List<String> getGroupExpensesList(String groupName) {
        Optional<Group> temp = groupRepository.findByGroupName(groupName);

        if (temp.isPresent()) {
            Group group = temp.get();
            List<Expense> groupExpenses = expenseRepository.findByGroup(group);

            List<String> expenseStrings = groupExpenses.stream()
                    .map(expense -> "Description: " + expense.getDescription() + ", Amount: " + expense.getAmount())
                    .collect(Collectors.toList());

            return expenseStrings;
        } else {
            // Group not found
            return Collections.emptyList();
        }
    }

    public BigDecimal getGroupTotal(String groupName) {
        Optional<Group> temp = groupRepository.findByGroupName(groupName);

        if (temp.isPresent()) {
            Group group = temp.get();
            List<Expense> groupExpenses = expenseRepository.findByGroup(group);

            // Calculate the total amount spent by the group
            BigDecimal groupTotal = groupExpenses.stream()
                    .map(Expense::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return groupTotal;
        } else {
            // Group not found
            return null;
        }
    }



}
