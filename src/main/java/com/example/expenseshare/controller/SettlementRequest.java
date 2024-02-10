package com.example.expenseshare.controller;

public class SettlementRequest {
    private String groupName;

    private String payerEmail;

    private String payeeEmail;

    private Double amount;

    public String getPayerEmail() {
        return payerEmail;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public Double getAmount() {
        return amount;
    }

    public String getGroupName() {
        return groupName;
    }
}
