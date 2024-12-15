package com.library.services;

import com.library.entities.Transaction;
import com.library.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;

//This Service automatically checks for returned date exceeding the due date and 
// Notifies the customers if they have to pay the fine.
@Service
public class NotificationService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    //Setting a Cron Job if not called mannually
    //@Scheduled(cron = "0 0 9 * * ?") //Runs Everyday at 9 AM
    
    public void notifyOverdueUsers() {
        // Fetch all transactions and filter for overdue ones where returnedAt is null
        List<Transaction> overdueTransactions = transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getReturnedAt() == null && // Check if the book hasn't been returned
                                       transaction.getDueDate() != null && // Ensure there's a valid due date
                                       LocalDate.now().isAfter(transaction.getDueDate())) // Compare dueDate with current date
                .collect(Collectors.toList());

        // Process each overdue transaction
        overdueTransactions.forEach(transaction -> {
            // Calculate the number of overdue days
            long overdueDays = LocalDate.now().toEpochDay() - transaction.getDueDate().toEpochDay();

            // Calculate the fine (e.g., $5 per overdue day)
            double fine = overdueDays * 5;

            // Print the notification
            System.out.println("User " + transaction.getUser().getName() +
                               " has an overdue book titled '" + transaction.getBook().getTitle() +
                               "'. Overdue by " + overdueDays + " days. Fine: $" + fine);
        });
    }

}
