package ch.hevs.bankservice;

import java.util.List;
import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.Category;
import ch.hevs.businessobject.Publisher;

public interface BookService {
	// Book Management
    List<Book> getAllBooks();
    Book findBookByIsbn(String id);
    void addBook(Book book);
    void deleteBookByIsbn(String isbn);
    List<Book> searchBooks(String searchTerm);


    // Category Management
    List<Category> getAllCategories();
    Category findCategoryById(Long id);
    void addCategory(Category category);
    void deleteCategoryById(Long id);

    // Publisher Management
    List<Publisher> getAllPublishers();
    Publisher findPublisherById(Long id);
    void addPublisher(Publisher publisher);
    void deletePublisherById(Long id);
}
