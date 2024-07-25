package com.leandroProject.apideinvestimentos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tb_accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;

    @OneToMany( mappedBy = "account")
    private List<AccountStock> accountStocks = new ArrayList<>();

    public Account() {
    }

    public Account(UUID accountId, String description, User user, BillingAddress billingAddress, List<AccountStock> accountStocks) {
        this.accountId = accountId;
        this.description = description;
        this.user = user;
        this.billingAddress = billingAddress;
        this.accountStocks = accountStocks;
    }
}
