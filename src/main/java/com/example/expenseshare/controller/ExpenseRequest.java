package com.example.expenseshare.controller;

import com.example.expenseshare.model.Expense;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ExpenseRequest {
        private String description;
        private BigDecimal amount;
        private Date date;
        private String category;
        private Long creatorId;
        private List<Long> debtorIds;
        private List<BigDecimal> amounts;

        private String groupName;


        public Long getCreatorId() {
            return creatorId;
        }


        public List<Long> getDebtorIds() {
            return debtorIds;
        }

        public List<BigDecimal> getAmounts() {
            return amounts;
        }
        public String getDescription() {
            return description;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public Date getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public String getGroupName() {
            return groupName;
        }


}
