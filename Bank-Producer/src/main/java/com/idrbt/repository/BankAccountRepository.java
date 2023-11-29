package com.idrbt.repository;

import com.idrbt.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Add custom query method to find BankAccount by accountNum
    BankAccount findByAccountNum(String accountNum);
}
