package com.bank.service;

import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.binding.BankUser;
import com.bank.entity.Bank;
import com.bank.repository.BankRepository;


@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public String createAccount(Bank bank) {

	    bank.setAccNumber(generateRandomNumber());

	    Bank save = bankRepo.save(bank);
	    sendRegistrationEmail(save);
	    return "Account Created Successfully";

	}
	
	private void sendRegistrationEmail(Bank bank) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(bank.getEmail());
        message.setSubject("Registration Successful");
        message.setText("Dear " + bank.getAccHolderName() + ",\n\n Your Account Opened Successfully."
        		+ "\nPlease refer to the following details:"
        		+ "\nAccount Number: " + bank.getAccNumber()
        		+ "\nBank Name: " + bank.getBankName()
        		+ "\nBranch Name: " + bank.getBranchName()
        		+ "\nBalance: " + bank.getBalance());

        javaMailSender.send(message);
	}

	@Override
	public Bank getAccountByAccNumber(Long accNumber) {
		
		Bank accountByAccNumber = bankRepo.findByAccNumber(accNumber);
		return accountByAccNumber;
	}

	@Override
	public List<Bank> getAllAccounts() {
		
		List<Bank> findAll = bankRepo.findAll();
		return findAll;
	}

	@Override
	public String addAmount(Long accNumber, Double amount, Bank bank) {
		
		Bank findByAccNumber = bankRepo.findByAccNumber(accNumber);
		
		if(findByAccNumber != null) {
			
			Double currentBalance = findByAccNumber.getBalance();
			currentBalance += amount;
			
			bank.setBalance(currentBalance);
			
			bankRepo.save(bank);
			sendDepositNotification(bank, amount);
			
			return "Amount Deposited Successfully to your Account :  "+currentBalance;
		}
			
			return "Account not found "+accNumber;
	}
	
	 private void sendDepositNotification(Bank bank, double amount) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(bank.getEmail());
	        message.setSubject("Deposit Notification");
	        message.setText("Dear " + bank.getAccHolderName() + ",\n\n"
	                + "An amount of " + amount + " has been deposited into your account."
	                + "\nYour current balance is: " + bank.getBalance());

	        javaMailSender.send(message);
	    }

	@Override
	public String updateAccountDetails(Long accNumber, Bank bank) {
		
		Bank findByAccNumber = bankRepo.findByAccNumber(accNumber);
		
		if(findByAccNumber != null) {
			
			bank.setAccHolderName(bank.getAccHolderName());
			bank.setEmail(bank.getEmail());
			bank.setPassword(bank.getPassword());
			
			bankRepo.save(bank);
			
			return "Acccount Details Updated Successfully";
		}
		
		return "Account not found "+accNumber;
	}

	@Override
	public String deleteAccount(Long accNumber) {
		
		Bank findByAccNumber = bankRepo.findByAccNumber(accNumber);
		
		if(findByAccNumber != null) {
			
			bankRepo.deleteByAccNumber(accNumber);
			return "Account Deleted Successfully";
		}
		
		return "Account not found "+accNumber;
	}
	
	public static long generateRandomNumber() {
        Random random = new Random();
        // Generate a random number between 1000000000 and 9999999999
        long randomNum = 1000000000L + random.nextInt(900000000);
        return randomNum;
    }

}
