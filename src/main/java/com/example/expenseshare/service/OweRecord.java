package com.example.expenseshare.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OweRecord {
    private String debtor;
    private String creditor;
    private BigDecimal amount;

    public OweRecord(String debtor, String creditor, BigDecimal amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

    // Getters and setters
}
