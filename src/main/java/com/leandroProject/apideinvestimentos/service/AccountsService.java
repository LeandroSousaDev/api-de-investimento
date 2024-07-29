package com.leandroProject.apideinvestimentos.service;

import com.leandroProject.apideinvestimentos.client.BrapiClient;
import com.leandroProject.apideinvestimentos.controller.DTO.AccountStockResponseDto;
import com.leandroProject.apideinvestimentos.controller.DTO.AssociateAccountStockDto;
import com.leandroProject.apideinvestimentos.entity.AccountStock;
import com.leandroProject.apideinvestimentos.entity.AccountStockId;
import com.leandroProject.apideinvestimentos.repository.AccountRepository;
import com.leandroProject.apideinvestimentos.repository.AccountStockRepository;
import com.leandroProject.apideinvestimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountsService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private AccountRepository accountRepository;

    private StockRepository stockRepository;

    private AccountStockRepository accountStockRepository;

    private BrapiClient brapiClient;

    public AccountsService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }


    public void associeteStock(String accountId, AssociateAccountStockDto associateDto) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(associateDto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                associateDto.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> listStocks(String accoundId) {
        var account = accountRepository.findById(UUID.fromString(accoundId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(as -> new AccountStockResponseDto(
                        as.getStock().getStockId(),
                        as.getQuantity(),
                        getTotal(as.getStock().getStockId(),
                                as.getQuantity())))
                .toList();
    }

    private double getTotal(String stockId, int quantity) {
        var response =brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }
}
