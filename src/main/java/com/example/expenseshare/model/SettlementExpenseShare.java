package com.example.expenseshare.model;


import jakarta.persistence.*;

@Entity
@Table(name = "settlement_expenseshare")
public class SettlementExpenseShare {

    @EmbeddedId
    private SettlementExpenseShareId id;

    @ManyToOne
    @MapsId("settlement")
    private Settlement settlement;

    @ManyToOne
    @MapsId("expenseShare")
    private ExpenseShare expenseShare;

    public SettlementExpenseShare() {

    }

    public SettlementExpenseShare(Settlement settlement, ExpenseShare expenseShare) {
        this.settlement = settlement;
        this.expenseShare = expenseShare;
        this.id = id;
    }

    public SettlementExpenseShareId getId() {
        return id;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public ExpenseShare getExpenseShare() {
        return expenseShare;
    }

    public void setSettlementExpenseShareId(SettlementExpenseShareId settlementExpenseShareId) {
        this.id = settlementExpenseShareId;
    }
}

