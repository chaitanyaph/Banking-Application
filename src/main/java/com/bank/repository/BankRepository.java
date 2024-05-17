package com.bank.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bank.entity.Bank;

import jakarta.transaction.Transactional;

public interface BankRepository extends JpaRepository<Bank, Serializable>{

    public Bank findByAccNumber(Long accNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM Bank b WHERE b.accNumber = :accNumber")
    public void deleteByAccNumber(Long accNumber);

    public List<Bank> findAll();
}
