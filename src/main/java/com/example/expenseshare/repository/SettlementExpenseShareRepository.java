package com.example.expenseshare.repository;

import com.example.expenseshare.model.Settlement;
import com.example.expenseshare.model.SettlementExpenseShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementExpenseShareRepository extends JpaRepository<SettlementExpenseShare,Long> {

}
