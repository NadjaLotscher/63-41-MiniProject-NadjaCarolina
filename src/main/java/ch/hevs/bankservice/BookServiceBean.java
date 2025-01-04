package ch.hevs.bankservice;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;
import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.Category;
import ch.hevs.businessobject.Publisher;

@Stateless
public class BookServiceBean implements BookService {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    // Book Management    
    @Override
    public List<Book> getAllBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return em.find(Book.class, isbn);
    }

    @Override
    public void addBook(Book book) {
        if (book.getCategory() != null) {
            book.setCategory(em.merge(book.getCategory()));
        }
        if (book.getPublisher() != null) {
            book.setPublisher(em.merge(book.getPublisher()));
        }
        em.persist(book);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        Book book = em.find(Book.class, isbn);
        if (book != null) {
            em.remove(book);
        } else {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
    }
    
    // Category Management
    @Override
    public List<Category> getAllCategories() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public Category findCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public void addCategory(Category category) {
        em.persist(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }

    // Publisher Management
    @Override
    public List<Publisher> getAllPublishers() {
        return em.createQuery("SELECT p FROM Publisher p", Publisher.class).getResultList();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return em.find(Publisher.class, id);
    }

    @Override
    public void addPublisher(Publisher publisher) {
        em.persist(publisher);
    }

    @Override
    public void deletePublisherById(Long id) {
        Publisher publisher = em.find(Publisher.class, id);
        if (publisher != null) {
            em.remove(publisher);
        }
    }
}
