package ch.hevs.test;

import java.sql.SQLException;
import org.junit.Test;
import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.User;
import ch.hevs.businessobject.Publisher;
import ch.hevs.businessobject.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import junit.framework.TestCase;

public class PopulateDB extends TestCase {

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        EntityTransaction tx = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU_unitTest");
            EntityManager em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            // Create Categories
            Category fiction = new Category("Fiction");
            Category fantasy = new Category("Fantasy");
            Category science = new Category("Science");
            Category thriller = new Category("Thriller");
            em.persist(fiction);
            em.persist(fantasy);
            em.persist(science);
            em.persist(thriller);

            // Publishers
            Publisher scholastic = new Publisher("Scholastic");
            Publisher penguin = new Publisher("Penguin Random House");
            Publisher harperCollins = new Publisher("HarperCollins");
            Publisher simonSchuster = new Publisher("Simon & Schuster");
            em.persist(scholastic);
            em.persist(penguin);
            em.persist(harperCollins);
            em.persist(simonSchuster);

            // Books with the 'available' field
            Book book1 = new Book("The Hunger Games", "Suzanne Collins", "9781407132082", scholastic, fiction, true);
            Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "9780747532743", scholastic, fantasy, true);
            Book book3 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "9780261102385", harperCollins, fantasy, false);
            Book book4 = new Book("A Brief History of Time", "Stephen Hawking", "9780553380163", penguin, science, true);
            Book book5 = new Book("Dune", "Frank Herbert", "9780441013593", penguin, science, true);
            Book book6 = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "9780307949486", penguin, thriller, true);
            Book book7 = new Book("Gone Girl", "Gillian Flynn", "9780307588371", simonSchuster, thriller, true);
            Book book8 = new Book("The Da Vinci Code", "Dan Brown", "9780307474278", penguin, thriller, true);
            Book book9 = new Book("The Silence of the Lambs", "Thomas Harris", "9780312924584", simonSchuster, thriller, true);
            Book book10 = new Book("Shutter Island", "Dennis Lehane", "9780061898815", harperCollins, thriller, false);

            em.persist(book1);
            em.persist(book2);
            em.persist(book3);
            em.persist(book4);
            em.persist(book5);
            em.persist(book6);
            em.persist(book7);
            em.persist(book8);
            em.persist(book9);
            em.persist(book10);

            // Users
            User user1 = new User("Alice", "Smith", "alice.smith@employeLibrary.com", "Library");
            User user2 = new User("Bob", "Johnson", "bob.johnson@readerLibrary.com", "Reader");
            User user3 = new User("Charlie", "Brown", "charlie.brown@employeLibrary.com", "Library");
            em.persist(user1);
            em.persist(user2);
            em.persist(user3);

            // Commit the transaction
            tx.commit();

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
