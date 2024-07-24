package com.leandroProject.apideinvestimentos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_stock")
@Data
public class Stock {

    @Id
    private String stockId;

    private String description;

    public Stock() {
    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }
}
