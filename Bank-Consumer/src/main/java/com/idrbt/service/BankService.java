package com.idrbt.service;

import com.idrbt.entity.BankAccount;



public interface BankService {
    Iterable<BankAccount> getAllBankData();
    void saveBankData(BankAccount bankData);
}
