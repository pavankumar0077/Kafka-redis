package com.idrbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idrbt.BankProducer;
import com.idrbt.entity.BankAccount;
import com.idrbt.redis.CacheService;
import com.idrbt.repository.BankAccountRepository;

@RestController
@RequestMapping("/api/bank")
public class BankKafkaController {

    private final BankProducer bankProducer;
    private final CacheService cacheService;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankKafkaController(BankProducer bankProducer, CacheService cacheService, BankAccountRepository bankAccountRepository) {
        this.bankProducer = bankProducer;
        this.cacheService = cacheService;
        this.bankAccountRepository = bankAccountRepository;
        
    }

    @PostMapping("/publish-account")
    public ResponseEntity<String> publishBankAccount(@RequestBody BankAccount bankAccount) {
        bankProducer.produceBankAccount(bankAccount);
        return ResponseEntity.ok("JSON Message sent to Kafka topic");
    }

    @GetMapping("/cached-data/{key}")
    public ResponseEntity<String> getCachedBankAccountData(@PathVariable String key) throws JsonProcessingException {
        // Log key for debugging
        System.out.println("Received key: " + key);

        // Retrieve BankAccount from the database
        BankAccount bankAccount = getBankAccountByKeyFromDatabase(key);

        // Log retrieved BankAccount for debugging
        System.out.println("Retrieved BankAccount: " + bankAccount);

        if (bankAccount != null) {
            // Cache the BankAccount and return the cached data
            String cachedData = cacheService.convertBankAccountToJson(bankAccount);
            return ResponseEntity.ok("Cached Bank Account Data: " + cachedData);
        } else {
            // Log if BankAccount is null
            System.out.println("BankAccount is null.");
            return ResponseEntity.notFound().build();
        }
    }
    
 // Replace this with your actual logic to retrieve BankAccount from the database
    private BankAccount getBankAccountByKeyFromDatabase(String key) {
        // Implement logic to retrieve BankAccount for the given key from the database
        // For example, if you are using JPA repository:
        return bankAccountRepository.findByAccountNum(key);
    }
    
    
    
}
