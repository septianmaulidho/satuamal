package com.testing.LastProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.LastProject.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
