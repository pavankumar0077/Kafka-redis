package com.idrbt.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idrbt.entity.BankAccount;
import com.idrbt.service.BankService;

@Component
public class BankConsumer {

    private final BankService bankService;

    public BankConsumer(BankService bankService) {
        this.bankService = bankService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String bankAccountJson) {
        try {
            BankAccount bankAccount = convertJsonToBankAccount(bankAccountJson);
            // Save the received BankAccount into the MySQL database
            bankService.saveBankData(bankAccount);
        } catch (JsonProcessingException e) {
            // Log or handle the exception
            e.printStackTrace();
        }
    }
    
    private BankAccount convertJsonToBankAccount(String bankAccountJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(bankAccountJson, BankAccount.class);
    }


}
