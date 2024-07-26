package com.leandroProject.apideinvestimentos.service;

import com.leandroProject.apideinvestimentos.controller.DTO.CreateStockDto;
import com.leandroProject.apideinvestimentos.entity.Stock;
import com.leandroProject.apideinvestimentos.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
