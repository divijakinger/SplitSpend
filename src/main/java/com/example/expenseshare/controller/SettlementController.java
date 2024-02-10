package com.example.expenseshare.controller;

import com.example.expenseshare.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settlement")
public class SettlementController {
    @Autowired
    private SettlementService settlementService;

    @PostMapping("/settle")
    public ResponseEntity<String> settleBalance(@RequestBody SettlementRequest settlementRequest) {
        try {
            settlementService.settleBalance(settlementRequest);
            return ResponseEntity.ok("Settlement successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during settlement");
        }
    }
}
