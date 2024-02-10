package com.example.expenseshare.service;

import com.example.expenseshare.model.Expense;
import com.example.expenseshare.model.ExpenseShare;
import com.example.expenseshare.model.Group;
import com.example.expenseshare.repository.GroupRepository;
import com.example.expenseshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.expenseshare.repository.ExpenseRepository;

import com.example.expenseshare.repository.ExpenseShareRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseShareRepository expenseShareRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void addExpenseWithShares(Expense expense, List<Long> debtorIds, List<BigDecimal> amounts, String groupName){
        Optional<Group> temp_group = groupRepository.findByGroupName(groupName);
        if (temp_group.isEmpty()) {
            throw new IllegalStateException("Group does not exist");
        } else {
            Group group = temp_group.get();
            expense.setGroup(group);
            Expense savedExpense = expenseRepository.save(expense);
            for (int i = 0; i < debtorIds.size(); i++) {
                ExpenseShare expenseShare = new ExpenseShare();
                expenseShare.setDebtor(userRepository.getById(debtorIds.get(i)));
                expenseShare.setCreator(expense.getCreator());
                expenseShare.setExpense(savedExpense);
                expenseShare.setAmount(amounts.get(i));

                expenseShareRepository.save(expenseShare);
            }
        }
    }



}
