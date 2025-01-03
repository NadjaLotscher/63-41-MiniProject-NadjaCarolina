package ch.hevs.bankservice;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ch.hevs.businessobject.Book;

@Stateless
public class BookServiceBean implements BookService {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    @Override
    public List<Book> getAllBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public void addBook(Book book) {
    	// Ensure associations are managed entities
        if (book.getCategory() != null) {
            book.setCategory(em.merge(book.getCategory())); // Merge category
        }
        if (book.getPublisher() != null) {
            book.setPublisher(em.merge(book.getPublisher())); // Merge publisher
        }

        // Persist the book
        em.persist(book);
    }

    @Override
    public void deleteBook(Book book) {
        Book managedBook = em.merge(book);
        em.remove(managedBook);
    }

    @Override
    public Book findBookById(Long id) {
        return em.find(Book.class, id);
    }
}
