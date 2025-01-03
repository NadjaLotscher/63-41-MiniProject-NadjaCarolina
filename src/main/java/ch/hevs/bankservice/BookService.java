package ch.hevs.bankservice;

import java.util.List;
import ch.hevs.businessobject.Book;

public interface BookService {
    List<Book> getAllBooks();
    void addBook(Book book);
    void deleteBook(Book book);
    Book findBookById(Long id);
}
