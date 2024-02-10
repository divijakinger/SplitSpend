package com.example.expenseshare.controller;


import com.example.expenseshare.model.Expense;
import com.example.expenseshare.model.User;
import com.example.expenseshare.repository.UserRepository;
import com.example.expenseshare.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    private final UserRepository userRepository;

    @Autowired
    public ExpenseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addExpense")
    public String addExpense(@RequestBody ExpenseRequest expenseRequest){

        Expense expense = mapToExpense(expenseRequest);

        List<Long> debtorIds = expenseRequest.getDebtorIds();
        List<BigDecimal> amounts = expenseRequest.getAmounts();
        String groupName = expenseRequest.getGroupName();

        expenseService.addExpenseWithShares(expense, debtorIds, amounts, groupName);

        return "Expense added successfully";

    }

    private Expense mapToExpense(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setDate(expenseRequest.getDate());
        expense.setCategory(expenseRequest.getCategory());

        User creator = userRepository.findById(expenseRequest.getCreatorId()).orElse(null);
        expense.setCreator(creator);

        return expense;
    }




}
