package com.example.expenseshare.model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "expense_share")
public class ExpenseShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareId;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private User debtor;

    @ManyToOne
    @JoinColumn(name = "creditor_id")
    private User creditor;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    private BigDecimal amount;

    @OneToMany(mappedBy = "expenseShare")
    private Set<SettlementExpenseShare> settlementExpenseShares = new HashSet<>();

    private boolean settled;

    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }

    public void setCreator(User creator) {
        this.creditor = creator;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getDebtor() {
        return debtor;
    }

    public User getCreator() {
        return creditor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Expense getExpense() {
        return expense;
    }

    public Long getExpenseShareId() {
        return shareId;
    }
}
