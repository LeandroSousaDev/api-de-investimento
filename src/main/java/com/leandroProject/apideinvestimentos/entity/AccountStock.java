package com.leandroProject.apideinvestimentos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_accountStock")
@Data
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stack_id")
    private Stock stock;

    private int quantity;

    public AccountStock() {
    }

    public AccountStock(AccountStockId id, Account account, Stock stock, int quantity) {
        this.id = id;
        this.account = account;
        this.stock = stock;
        this.quantity = quantity;
    }
}
