package com.bank.service;

import java.util.List;

import com.bank.binding.BankUser;
import com.bank.entity.Bank;

public interface BankService {

	public String createAccount(Bank bank);
	
	public Bank getAccountByAccNumber(Long accNumber);
	
	public List<Bank> getAllAccounts();
	
	public String addAmount(Long accNumber, Double amount, Bank bank);
	
	public String updateAccountDetails(Long accNumber, Bank bank);
	
	public String deleteAccount(Long accNumber);
	
}
