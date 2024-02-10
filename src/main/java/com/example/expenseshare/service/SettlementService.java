package com.example.expenseshare.service;

import com.example.expenseshare.controller.SettlementRequest;
import com.example.expenseshare.model.*;
import com.example.expenseshare.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SettlementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettlementRepository settlementRepository;

    @Autowired
    private ExpenseShareRepository expenseShareRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SettlementExpenseShareRepository settlementExpenseShareRepository;

    @Autowired
    private EntityManager entityManager;


    public void settleBalance(SettlementRequest settlementRequest) {
        String groupName = settlementRequest.getGroupName();
        String payerEmail = settlementRequest.getPayerEmail();
        String receiverEmail = settlementRequest.getPayeeEmail();
        Double amount = settlementRequest.getAmount();

        Group group = groupRepository.findByGroupName(groupName).get();


        User payer = userRepository.findByEmail(payerEmail).orElseThrow(() -> new EntityNotFoundException("Payer not found"));
        User receiver = userRepository.findByEmail(receiverEmail).orElseThrow(() -> new EntityNotFoundException("Payee not found"));


        Settlement settlement = new Settlement(payer, receiver, amount, new Date());
        settlementRepository.save(settlement);
        //get current settlement id
        Long settlementId = settlement.getSettlementId();

        updateSettlementExpenseShare(payer, receiver, group, settlement);

    }

    private void updateSettlementExpenseShare(User payer, User receiver, Group group, Settlement settlement) {
        // Fetch all ExpenseShare entities between the payer and receiver in the group where settled is false
        List<ExpenseShare> expenseShares = expenseShareRepository.findByCreditorAndDebtorAndExpense_GroupAndSettled(receiver,payer, group,false);

        System.out.println("expenseShares: " + expenseShares);

        for (ExpenseShare expenseShare : expenseShares) {

            SettlementExpenseShare settlementExpenseShare = new SettlementExpenseShare();
            SettlementExpenseShareId settlementExpenseShareId = new SettlementExpenseShareId();
            settlementExpenseShareId.setSettlement(settlement);
            settlementExpenseShareId.setExpenseShare(expenseShare);
            settlementExpenseShare.setSettlementExpenseShareId(settlementExpenseShareId);
            // Save the new SettlementExpenseShare entity
            settlementExpenseShareRepository.save(settlementExpenseShare);

            expenseShare.setSettled(true);

            // Save the updated ExpenseShare entity
            expenseShareRepository.save(expenseShare);

        }
    }
}
