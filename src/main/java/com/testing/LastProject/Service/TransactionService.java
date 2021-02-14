package com.testing.LastProject.Service;

import java.util.List;

import com.testing.LastProject.model.Transaction;
import com.testing.LastProject.payload.TransactionPayload;

public interface TransactionService {
	public  Transaction create(TransactionPayload transactionPayload);
	public List<Transaction> read();
	public Transaction update(String id, TransactionPayload transactionPayload);
}
