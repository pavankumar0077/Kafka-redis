package com.idrbt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idrbt.entity.BankAccount;
import com.idrbt.redis.CacheService;

@Service
public class BankProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankProducer.class);

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CacheService cacheService;


    public BankProducer(KafkaTemplate<String, String> kafkaTemplate, CacheService cacheService) {
        this.kafkaTemplate = kafkaTemplate;
        this.cacheService = cacheService;
    }

//    public void produceBankAccount(BankAccount bankAccount) {
//        try {
//            String message = convertBankAccountToJson(bankAccount);
//            kafkaTemplate.send(topicName, message);
//            LOGGER.info("Sent message to Kafka topic: {}", topicName);
//
//            // Cache the produced data in Redis
//            cacheService.convertBankAccountToJson(bankAccount);
//
//            // Save producer data in Redis cache
//            cacheService.cacheProducerData(bankAccount.getAccountNum(), message);
//        } catch (JsonProcessingException e) {
//            LOGGER.error("Error converting BankAccount to JSON: {}", e.getMessage());
//        }
//    }
    
    
    public void produceBankAccount(BankAccount bankAccount) {
        try {
            // Convert BankAccount to JSON
            String message = convertBankAccountToJson(bankAccount);

            // Generate a unique key for this message
            String messageKey = generateUniqueKey(bankAccount);
            
            // Print the generated key to the console
            LOGGER.info("Generated Key: {}", messageKey);

            // Send the message to Kafka
            kafkaTemplate.send(topicName, message);
            LOGGER.info("Sent message to Kafka topic: {}", topicName);

            // Cache the produced data in Redis
            cacheService.convertBankAccountToJson(bankAccount);

            // Save producer data in Redis cache with the generated key
            cacheService.cacheProducerData(messageKey, message);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting BankAccount to JSON: {}", e.getMessage());
        }
    }

    private String generateUniqueKey(BankAccount bankAccount) {
        // You need to implement a logic to generate a unique key based on the BankAccount data
        // For example, you can concatenate accountNum and some timestamp
        return bankAccount.getAccountNum() + "_" + System.currentTimeMillis();
    }
    
    
    
//    @CachePut(value = "producerDataCache", key = "#messageKey")
//    public String cacheProducerData(String messageKey, String message) {
//        // Optionally, you can perform additional processing on the message before caching
//        return message;
//    }

    private String convertBankAccountToJson(BankAccount bankAccount) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bankAccount);
    }
    
//    @CachePut(value = "bankAccountCache", key = "#bankAccount.accountNum")
//    public String cacheBankAccount(BankAccount bankAccount) throws JsonProcessingException {
//        LOGGER.info("Caching Bank Account Data: {}", bankAccount);
//        return convertBankAccountToJson(bankAccount);
//    }
}
