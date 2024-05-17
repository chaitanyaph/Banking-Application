package com.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Bank {
	
	@Id
	private Long accNumber;
	private String email;
	private String password;
	private String bankName;
	private String accHolderName;
	private String branchName;
	private Double balance;
	
}
