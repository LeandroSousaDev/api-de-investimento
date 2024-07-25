package com.leandroProject.apideinvestimentos.repository;

import com.leandroProject.apideinvestimentos.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, String> {
}
