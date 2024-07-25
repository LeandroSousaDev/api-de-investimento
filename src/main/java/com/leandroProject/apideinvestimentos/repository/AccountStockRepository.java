package com.leandroProject.apideinvestimentos.repository;

import com.leandroProject.apideinvestimentos.entity.AccountStock;
import com.leandroProject.apideinvestimentos.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
