package com.library;

import com.library.entities.Book;
import com.library.entities.User;
import com.library.entities.Transaction;
import com.library.services.BookService;
import com.library.services.UserService;
import com.library.services.TransactionService;
import com.library.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.util.List;

@ShellComponent
public class LibraryCLI {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private NotificationService notificationService;
    
    @ShellMethod(value = "Add a user", key = "add-user")
    public String addUser(String name, String email, String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role.toUpperCase()));
        userService.createUser(user);
        return "User added successfully: " + user.getName();
    }

    @ShellMethod(value = "List all users", key = "list-users")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    // Book Commands
    @ShellMethod(value = "Add a book", key = "add-book")
    public String addBook(String title, String author, String genre, int publicationYear, boolean available) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublicationYear(publicationYear);
        book.setAvailable(available);
        bookService.addBook(book);
        return "Book added successfully: " + book.getTitle();
    }

    @ShellMethod(value = "List all books", key = "list-books")
    public List<Book> listBooks() {
        return bookService.getAllBooks();
    }

    // Transaction Commands
    @ShellMethod(value = "Issue a book", key = "issue-book")
    public String issueBook(Long userId, Long bookId, String issuedAt, String dueDate) {
        Transaction transaction = new Transaction();
        transaction.setUser(userService.getUserById(userId));
        transaction.setBook(bookService.getBookById(bookId));
        transaction.setIssuedAt(LocalDate.parse(issuedAt));
        transaction.setDueDate(LocalDate.parse(dueDate));
        Transaction savedTransaction = transactionService.issueBook(transaction);
        return "Book issued successfully to user ID: " + userId + ". Transaction ID: " + savedTransaction.getId();
    }
    
 // Transaction Commands
    @ShellMethod(value = "Return a book", key = "return-book")
    public String returnBook(Long transactionID, String returnDate) {
        // Parse the return date from the provided string
        LocalDate returnedAt = LocalDate.parse(returnDate);

        // Call the service layer to process the return
        Transaction updatedTransaction = transactionService.returnBook(transactionID, returnedAt);

        // Build the response message
        String response = "Book returned successfully for transaction ID: " + transactionID + ". ";
        if (updatedTransaction.getFine() > 0) {
            response += "Fine incurred: $" + updatedTransaction.getFine();
        } else {
            response += "No Fine Incurred.";
        }

        // Return the response to the CLI
        return response;
    }

 // Notification Command
    @ShellMethod(value = "Notify users with overdue books.", key = "notify-overdue-users")
    public void notifyOverdueUsers() {
        notificationService.notifyOverdueUsers();
    }


    @ShellMethod(value = "List all transactions", key = "list-transactions")
    public List<Transaction> listTransactions() {
        return transactionService.getAllTransactions();
   }
}
