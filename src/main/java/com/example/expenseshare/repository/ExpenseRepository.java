package com.example.expenseshare.repository;


import com.example.expenseshare.model.Expense;
import com.example.expenseshare.model.Group;
import com.example.expenseshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    List<Expense> findByGroup(Group group);

    List<Expense> findByGroup_GroupId(Long groupId);

    List<Expense> findByGroup_GroupIdAndCreator(Long groupId, User user);
}