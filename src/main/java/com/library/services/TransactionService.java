package com.library.services;

import com.library.entities.Transaction;
import com.library.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction issueBook(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    
    //Creating a Service returnBook() to calculate the fine if the return date has exceeded the Due Date 
    
    public Transaction returnBook ( Long transactionID, LocalDate returnDate) {
    	
    	//Fetching for Transaction
    	Transaction transaction = transactionRepository.findById(transactionID).orElseThrow(() -> new RuntimeException("Transaction Not Found"));
    	
    	//Updating the return Date
    	transaction.setReturnedAt(returnDate);
    	
    	//Calculating the Fine by if Overdue
    	if(returnDate.isAfter(transaction.getDueDate())) {
    		
    		//ChronoUnit can be used to measure the difference between two LocalDate
    		long overdueDays = ChronoUnit.DAYS.between(transaction.getDueDate(), returnDate);
    		System.out.println("Overdue Days: " + overdueDays);
    		transaction.setFine(overdueDays * 5.0);
    	}
    	else {
    		transaction.setFine(0.0);
    	}
    	
    	return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
