package com.example.expenseshare.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class SettlementExpenseShareId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Settlement settlement;

    @ManyToOne
    private ExpenseShare expenseShare;

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public void setExpenseShare(ExpenseShare expenseShare) {
        this.expenseShare = expenseShare;
    }
}