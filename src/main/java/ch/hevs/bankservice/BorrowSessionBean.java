package ch.hevs.bankservice;

import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.BorrowList;
import ch.hevs.businessobject.User;


@Stateful
public class BorrowSessionBean {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    private List<String> bookIdsToBorrow = new ArrayList<>(); // List of book IDs to borrow

    // Add a book to the borrow list
    public void addBookToBorrow(String string) {
    	// Fetch the book entity from the database
        Book book = em.find(Book.class, string);
        if (book == null) {
            throw new IllegalArgumentException("Book with ID " + string + " does not exist.");
        }

        // Check if the book is available
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book with ID " + string + " is not available.");
        }

        // Add the book to the borrow list
        bookIdsToBorrow.add(string);
    }

    // Remove a book from the borrow list
    public void removeBookFromBorrow(String bookId) {
        bookIdsToBorrow.remove(bookId);
    }

    // Get the current borrow list
    public List<Book> getBorrowList() {
    	List<Book> borrowList = new ArrayList<>();
        for (String bookId : bookIdsToBorrow) {
            Book book = em.find(Book.class, bookId);
            if (book != null) {
                borrowList.add(book);
            }
        }
        return borrowList;
    }

    // Confirm borrowing the books
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void confirmBorrowing(Long userId) {
        if (bookIdsToBorrow.isEmpty()) {
            throw new IllegalStateException("The borrow list is empty.");
        }

        // Create a new BorrowList entity
        BorrowList borrowList = new BorrowList();
        
        // Generate a default name (e.g., "Borrowed on [current date]")
        String defaultName = "Borrowed on " + LocalDate.now();
        borrowList.setName(defaultName);
        borrowList.setStatus("Confirmed");

        // Fetch the User entity
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
        borrowList.setUser(user);

        // Fetch all books in the borrow list
        List<Book> books = new ArrayList<>();
        for (String bookId : bookIdsToBorrow) {
            Book book = em.find(Book.class, bookId);
            if (book != null) {
                books.add(book);
                book.setAvailable(false); // Mark the book as unavailable
                em.merge(book); // Update the book in the database
            }
        }
        borrowList.setBooks(books);

        // Persist the BorrowList
        em.persist(borrowList);

        // Clear the session borrow list
        bookIdsToBorrow.clear();
    }

}
