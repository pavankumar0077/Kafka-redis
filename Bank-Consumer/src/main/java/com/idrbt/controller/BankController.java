package com.idrbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idrbt.entity.BankAccount;
import com.idrbt.service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/all")
    public Iterable<BankAccount> getAllBankData() {
        return bankService.getAllBankData();
    }
}
