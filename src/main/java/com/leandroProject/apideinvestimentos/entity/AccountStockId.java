package com.leandroProject.apideinvestimentos.entity;

import jakarta.persistence.Embeddable;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class AccountStockId {

    private UUID accountId;

    private String stockId;

    public AccountStockId() {
    }

    public AccountStockId(UUID accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }
}
