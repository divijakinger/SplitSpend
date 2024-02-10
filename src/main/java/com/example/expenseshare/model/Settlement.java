package com.example.expenseshare.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "settlement")
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settlementId;


    @Column(name = "payer_id")
    private Long payer_id;

    @Column(name = "receiver_id")
    private Long receiver_id;

    private double amount;

    private Date date;

    @OneToMany(mappedBy = "settlement")
    private Set<SettlementExpenseShare> settlementExpenseShares = new HashSet<>();

    public Settlement(User payer, User payee, Double amount, Date date) {
        this.payer_id = payer.getId();
        this.receiver_id = payee.getId();
        this.amount = amount;
        this.date = date;
    }

    public Long getSettlementId() {
        return settlementId;
    }

}
