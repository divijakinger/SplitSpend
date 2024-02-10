package com.example.expenseshare.repository;

import com.example.expenseshare.model.Expense;
import com.example.expenseshare.model.ExpenseShare;
import com.example.expenseshare.model.Group;
import com.example.expenseshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShare, Long> {
    List<ExpenseShare> findByExpense_Group(Group group);

    List<ExpenseShare> findByExpense_ExpenseId(Long expenseId);

    List<ExpenseShare> findByExpense_GroupAndDebtor(Group group, User user);

    List<ExpenseShare> findByCreditorAndDebtorAndExpense_GroupAndSettled(User creditor, User debtor, Group group, boolean settled);

}
