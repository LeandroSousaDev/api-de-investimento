package com.leandroProject.apideinvestimentos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_billingAddress")
@Data
public class BillingAddress {

    @Id
    @Column(name = "account_id")
    private UUID id;

    private String street;

    private int number;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    public BillingAddress() {
    }

    public BillingAddress(UUID id, String street, int number, Account account) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.account = account;
    }
}
