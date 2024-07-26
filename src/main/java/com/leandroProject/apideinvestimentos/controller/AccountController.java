package com.leandroProject.apideinvestimentos.controller;

import com.leandroProject.apideinvestimentos.controller.DTO.AccountStockResponseDto;
import com.leandroProject.apideinvestimentos.controller.DTO.AssociateAccountStockDto;
import com.leandroProject.apideinvestimentos.service.AccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {


    private AccountsService accountsService;

    public AccountController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }


    @PostMapping("/{accountId}/stock")
    public ResponseEntity<Void> associeteStock(@PathVariable("accountId") String accountId,
                                   @RequestBody AssociateAccountStockDto associateDto) {
    accountsService.associeteStock(accountId, associateDto);

    return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stock")
    public ResponseEntity<List<AccountStockResponseDto>> listStock(@PathVariable("accountId") String accoundId) {
        var stocks = accountsService.listStocks(accoundId);

        return ResponseEntity.ok(stocks);
    }
}
