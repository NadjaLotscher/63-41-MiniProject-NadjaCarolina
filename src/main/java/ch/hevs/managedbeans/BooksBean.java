package ch.hevs.managedbeans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import java.io.Serializable;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.Category;
import ch.hevs.businessobject.Publisher;
import ch.hevs.bankservice.BookService;

@Named
@SessionScoped
public class BooksBean implements Serializable {

    @EJB
    private BookService bookService;

    private Book book = new Book();
    private List<Book> cachedBooks;
    private List<Category> cachedCategories;
    private List<Publisher> cachedPublishers;

    // Fields for dropdown selection
    private Long categoryId;
    private Long publisherId;
    
    @PostConstruct
    public void init() {
		try {
			// use JNDI to inject reference to bank EJB
			InitialContext ctx = new InitialContext();
			bookService = (BookService) ctx.lookup("java:global/63-41-MINIPROJECT-BOOK-0.0.1-SNAPSHOT/BooksBean!ch.hevs.bankservice.BookService");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    
    // Getter & Setter for book
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    // Getter and Setter for categoryId
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Getter and Setter for publisherId
    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }
    
    public List<Book> getBooks() {
        if (cachedBooks == null) { // Cache the books for the session
            cachedBooks = bookService.getAllBooks();
        }
        return cachedBooks;
    }

    public void refreshBooks() {
        cachedBooks = bookService.getAllBooks(); // Explicit refresh if needed
    }
    
    public String viewBookDetails(Book book) {
        this.book = book; // Store the selected book for the session
        return "bookDetails.xhtml?faces-redirect=true";
    }
    
    public List<Category> getAvailableCategories() {
        if (cachedCategories == null) { // Cache categories for session
            cachedCategories = bookService.getAllCategories();
        }
        return cachedCategories;
    }

    public List<Publisher> getAvailablePublishers() {
        if (cachedPublishers == null) { // Cache publishers for session
            cachedPublishers = bookService.getAllPublishers();
        }
        return cachedPublishers;
    }

    public void refreshCategoriesAndPublishers() {
        cachedCategories = bookService.getAllCategories();
        cachedPublishers = bookService.getAllPublishers(); // Explicit refresh if needed
    }
    
    public String saveBook() {
        // Validate category and publisher IDs
        if (categoryId == null || categoryId <= 0) {
            throw new IllegalArgumentException("Invalid Category ID. Please select a valid category.");
        }
        if (publisherId == null || publisherId <= 0) {
            throw new IllegalArgumentException("Invalid Publisher ID. Please select a valid publisher.");
        }

        // Fetch the selected Category and Publisher
        Category selectedCategory = bookService.findCategoryById(categoryId);
        Publisher selectedPublisher = bookService.findPublisherById(publisherId);

        // Validate fetched Category and Publisher
        if (selectedCategory == null) {
            throw new IllegalArgumentException("Category not found for the given ID: " + categoryId);
        }
        if (selectedPublisher == null) {
            throw new IllegalArgumentException("Publisher not found for the given ID: " + publisherId);
        }

        // Establish relationships and save the book
        book.setCategory(selectedCategory);
        book.setPublisher(selectedPublisher);
        bookService.addBook(book);

        // Reset state for subsequent use
        book = new Book();
        categoryId = null;
        publisherId = null;
        refreshBooks();

        return "allBooks.xhtml?faces-redirect=true"; // Redirect to book list
    }

    
    public String deleteBook() {
        // Validate ISBN input
        if (this.book == null || this.book.getIsbn() == null) {
            throw new IllegalArgumentException("Invalid ISBN.");
        }

        // Use the ISBN of the currently selected book
        bookService.deleteBookByIsbn(this.book.getIsbn());

        // Reset state and refresh the book list
        book = new Book();
        refreshBooks();

        return "allBooks.xhtml?faces-redirect=true"; // Redirect to book list
    }

}
