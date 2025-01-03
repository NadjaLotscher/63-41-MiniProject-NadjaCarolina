package ch.hevs.managedbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import java.io.Serializable;
import java.util.List;
import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.Category;
import ch.hevs.businessobject.Publisher;
import ch.hevs.bankservice.BookService;
import ch.hevs.bankservice.CategoryService;
import ch.hevs.bankservice.PublisherService;

@Named
@RequestScoped
public class BookBean implements Serializable {

    @EJB
    private BookService bookService;

    @EJB
    private CategoryService categoryService; // Service to fetch categories
    
    @EJB
    private PublisherService publisherService; // Service to fetch publishers

    private Book newBook = new Book(); // Book to capture user input

    // Getter and Setter for newBook
    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    // Retrieve all books
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
    
    // Retrieve all available categories
    public List<Category> getAvailableCategories() {
        return categoryService.getAllCategories();
    }
    
    // retrieve all available publishers
    public List<Publisher> getAvailablePublishers() {
        return publisherService.getAllPublishers();
    }

    // Save a new book
    public String saveBook() {
        bookService.addBook(newBook);
        newBook = new Book(); // Reset newBook after saving
        return "allBooks.xhtml?faces-redirect=true"; // Redirect to the books list
    }
}
