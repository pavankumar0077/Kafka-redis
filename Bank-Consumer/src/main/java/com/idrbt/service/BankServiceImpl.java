package com.idrbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idrbt.entity.BankAccount;
import com.idrbt.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Iterable<BankAccount> getAllBankData() {
        return bankRepository.findAll();
    }

    @Override
    public void saveBankData(BankAccount bankAccount) {
        // Save the received BankData into the MySQL database
        bankRepository.save(bankAccount);
    }
}
