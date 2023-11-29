package com.idrbt.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idrbt.entity.BankAccount;

@Service
public class CacheService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

	@Cacheable("bankAccountCache")
	public String convertBankAccountToJson(BankAccount bankAccount) throws JsonProcessingException {
	ObjectMapper objectMapper = new ObjectMapper();
	return objectMapper.writeValueAsString(bankAccount);
	}

	@CachePut(value = "bankAccountCache", key = "#bankAccount.accountNum")
	public String cacheBankAccount(BankAccount bankAccount) throws JsonProcessingException {
	    return convertBankAccountToJson(bankAccount);
	}

	// New method to save producer data in Redis cache
    @CachePut(value = "producerDataCache", key = "#messageKey")
    public String cacheProducerData(String messageKey, String message) {
    	LOGGER.info("Caching producer data with key: {}", messageKey);
        // Optionally, you can perform additional processing on the message before caching
        return message;
    }
	
//	@CachePut(value = "bankAccountCache", key = "#bankAccount.accountNum")
//    public String cacheBankAccount(BankAccount bankAccount) throws JsonProcessingException {
//        System.out
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(bankAccount);
//    }
}