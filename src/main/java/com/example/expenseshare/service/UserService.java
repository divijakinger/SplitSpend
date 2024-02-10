package com.example.expenseshare.service;

import com.example.expenseshare.model.*;
import com.example.expenseshare.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseShareRepository expenseShareRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String addUser(String name, String email, String password){
        if (userRepository.existsByEmail(email)) {
            return "User already registered with this email";
        }
        userRepository.save(new User(name, email, password));
        return "User added successfully";
    }


    public Map<String, Object> getUserGroupBalances(String userEmail, String groupName) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> currentUserOptional = userRepository.findByEmail(userEmail);
        Optional<Group> groupOptional = groupRepository.findByGroupName(groupName);

        if (currentUserOptional.isPresent() && groupOptional.isPresent()) {
            User currentUser = currentUserOptional.get();
            Group group = groupOptional.get();
            Long groupId = group.getGroupId();

            List<Expense> expenses = expenseRepository.findByGroup_GroupId(groupId);
            List<ExpenseShare> expenseShares = expenseShareRepository.findByExpense_Group(group);

            Map<Long, BigDecimal> netBalanceMap = new HashMap<>();

            for (ExpenseShare expenseShare : expenseShares) {
                User creditor = expenseShare.getExpense().getCreditor();
                User debtor = expenseShare.getDebtor();
                BigDecimal amount = expenseShare.getAmount();

                if (currentUser.equals(creditor)) {
                    netBalanceMap.merge(debtor.getId(), amount, BigDecimal::add);
                }

                if (currentUser.equals(debtor)) {
                    netBalanceMap.merge(creditor.getId(), amount.negate(), BigDecimal::add);
                }
            }

            Map<String, BigDecimal> finalBalanceMap = new HashMap<>();

            for (Map.Entry<Long, BigDecimal> entry : netBalanceMap.entrySet()) {
                User user = userRepository.findById(entry.getKey()).orElse(null);
                if (user != null) {
                    finalBalanceMap.put(user.getName(), entry.getValue());
                }
            }

            response.put("netBalances", finalBalanceMap);
            return response;
        } else {
            response.put("error", "User or Group not found");
            return response;
        }
    }






}
