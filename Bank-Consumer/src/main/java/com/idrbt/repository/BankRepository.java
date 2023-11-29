package com.idrbt.repository;

import org.springframework.data.repository.CrudRepository;

import com.idrbt.entity.BankAccount;

public interface BankRepository extends CrudRepository<BankAccount, Long> {
	
}
