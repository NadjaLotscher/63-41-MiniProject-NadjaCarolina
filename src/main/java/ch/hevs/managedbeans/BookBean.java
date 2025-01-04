package ch.hevs.managedbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
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
    private CategoryService categoryService;

    @EJB
    private PublisherService publisherService;

    private Book newBook = new Book(); // Reused for both creation and details

    // Fields for dropdown selection
    private Long categoryId;
    private Long publisherId;
    
    // check for isbn (for bookdetails view)
    @PostConstruct
    public void init() {
        String isbn = FacesContext.getCurrentInstance()
                                  .getExternalContext()
                                  .getRequestParameterMap()
                                  .get("isbn");
        if (isbn != null) {
            this.newBook = bookService.findBookByIsbn(isbn);
        }
    }

    // Getter and Setter for newBook
    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
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

    // Retrieve all books
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    // Action method for viewing book details
    public String viewBookDetails(Book book) {
        return "bookDetails.xhtml?faces-redirect=true&isbn=" + book.getIsbn();
    }


    // Retrieve all available categories
    public List<Category> getAvailableCategories() {
        return categoryService.getAllCategories();
    }

    // Retrieve all available publishers
    public List<Publisher> getAvailablePublishers() {
        return publisherService.getAllPublishers();
    }

    // Save a new book
    public String saveBook() {
        // Fetch the selected Category and Publisher using IDs
        Category selectedCategory = categoryService.findCategoryById(categoryId);
        Publisher selectedPublisher = publisherService.findPublisherById(publisherId);

        // Set the relationships in newBook
        newBook.setCategory(selectedCategory);
        newBook.setPublisher(selectedPublisher);

        // Save the book
        bookService.addBook(newBook);

        // Reset newBook and dropdown selections
        newBook = new Book();
        categoryId = null;
        publisherId = null;

        return "allBooks.xhtml?faces-redirect=true"; // Redirect to the books list
    }
}