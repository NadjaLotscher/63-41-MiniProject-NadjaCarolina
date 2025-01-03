package ch.hevs.managedbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;
import ch.hevs.businessobject.Book;

@Named
@RequestScoped
public class BookListBean implements Serializable {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    public List<Book> getBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}