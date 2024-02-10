package com.example.expenseshare.repository;

import com.example.expenseshare.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementRepository extends JpaRepository<Settlement,Long> {
}
