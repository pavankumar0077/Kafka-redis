package com.idrbt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountNum;
	private String accountHolderName;
	private double balance;

	public BankAccount() {
		super();
	}

	public BankAccount(Long id, String accountNum, String accountHolderName, double balance) {
		super();
		this.id = id;
		this.accountNum = accountNum;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", accountNum=" + accountNum + ", accountHolderName=" + accountHolderName
				+ ", balance=" + balance + "]";
	}

}
