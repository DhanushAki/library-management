package com.library.controllers;

import com.library.entities.Transaction;
import com.library.services.TransactionService;
import com.library.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private NotificationService notificationService;
    
    //Notifying the User for Overdue Fine 
    @GetMapping("/notify-overdue")
    public ResponseEntity<String> notifyOverdueUsers() {
    	notificationService.notifyOverdueUsers();
    	return ResponseEntity.ok("Overdue notifications sent successfully!");
    }

    // Issue a book
    @PostMapping("/issue")
    public Transaction issueBook(@RequestBody Transaction transaction) {
        return transactionService.issueBook(transaction);
    }
    
    // Return a Book
    @PutMapping("return/{id}")
    public ResponseEntity<String> returnBook( @PathVariable Long id, @RequestParam String returnDate) {
    	LocalDate returnedAt = LocalDate.parse(returnDate);
    	Transaction updatedTransaction = transactionService.returnBook(id, returnedAt);
    	
    	String response = "Book returned successfully. ";
    	
    	if(updatedTransaction.getFine() > 0) {
    		response += "Fine incurred: $" + updatedTransaction.getFine();
    	} else {
    		response += "No Fine Incurred.";
    	}
    	
    	return ResponseEntity.ok(response);
    	
    }
    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
  