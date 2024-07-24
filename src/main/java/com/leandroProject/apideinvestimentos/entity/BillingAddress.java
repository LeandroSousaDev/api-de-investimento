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

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

}
