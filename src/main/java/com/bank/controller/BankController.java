package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.binding.BankUser;
import com.bank.entity.Bank;
import com.bank.service.BankService;

@RestController
public class BankController {

	@Autowired
	private BankService bankService;
	
	@PostMapping("/bank/create")
	public ResponseEntity<String> createAccount(@RequestBody Bank bank){
		
		String createAccount = bankService.createAccount(bank);
		return new ResponseEntity<>(createAccount,HttpStatus.CREATED);
	}
	
	@GetMapping("/bank/{accNumber}")
	public ResponseEntity<Bank> getAccount(@PathVariable Long accNumber){
		
		Bank accountByAccNumber = bankService.getAccountByAccNumber(accNumber);
		return new ResponseEntity<>(accountByAccNumber,HttpStatus.OK);
	}
	
	@GetMapping("/bank")
	public ResponseEntity<List<Bank>> getAllAccounts(){
		
		List<Bank> allAccounts = bankService.getAllAccounts();
		return new ResponseEntity<>(allAccounts,HttpStatus.OK);
	}
	
	@PutMapping("/bank/{accNumber}/{amount}")
	public ResponseEntity<String> addAmount(@PathVariable Long accNumber, @PathVariable Double amount, @RequestBody Bank bank){
		
		String addAmount = bankService.addAmount(accNumber, amount, bank);
		return new ResponseEntity<>(addAmount,HttpStatus.OK);
	}
	
	@PutMapping("/bank/{accNumber}")
	public ResponseEntity<String> updateAccountDetail(@PathVariable Long accNumber, @RequestBody Bank bank){
		
		String updateAccountDetails = bankService.updateAccountDetails(accNumber, bank);
		return new ResponseEntity<>(updateAccountDetails,HttpStatus.OK);
	}
	
	@DeleteMapping("/bank/{accNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long accNumber){
		
		String deleteAccount = bankService.deleteAccount(accNumber);
		return new ResponseEntity<>(deleteAccount,HttpStatus.OK);
	}
}
