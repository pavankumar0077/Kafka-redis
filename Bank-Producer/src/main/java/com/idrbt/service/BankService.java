package com.idrbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idrbt.entity.BankAccount;
import com.idrbt.redis.CacheService;
import com.idrbt.repository.BankAccountRepository;

@Service
public class BankService {

    private final CacheService cacheService;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankService(CacheService cacheService, BankAccountRepository bankAccountRepository) {
        this.cacheService = cacheService;
        this.bankAccountRepository = bankAccountRepository;
    }

    public String getBankAccountDataForKey1() throws JsonProcessingException {
        BankAccount bankAccount = getBankAccountForKey1();
        return bankAccount != null ? cacheService.convertBankAccountToJson(bankAccount) : null;
    }

    public String getBankAccountDataForKey2() throws JsonProcessingException {
        BankAccount bankAccount = getBankAccountForKey2();
        return bankAccount != null ? cacheService.convertBankAccountToJson(bankAccount) : null;
    }

    // Replace these methods with your actual logic to retrieve BankAccount objects
    private BankAccount getBankAccountForKey1() {
        // Implement logic to retrieve BankAccount for key 1
    	return bankAccountRepository.findByAccountNum("key1");
    }

    private BankAccount getBankAccountForKey2() {
        // Implement logic to retrieve BankAccount for key 2
    	return bankAccountRepository.findByAccountNum("key2");
    }
}
