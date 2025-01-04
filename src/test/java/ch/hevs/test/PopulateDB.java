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

            // Books
            Book book1 = new Book("The Hunger Games", "Suzanne Collins", "9781407132082", 11.47, scholastic, fiction);
            Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "9780747532743", 12.99, scholastic, fantasy);
            Book book3 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "9780261102385", 15.99, harperCollins, fantasy);
            Book book4 = new Book("A Brief History of Time", "Stephen Hawking", "9780553380163", 10.99, penguin, science);
            Book book5 = new Book("Dune", "Frank Herbert", "9780441013593", 9.99, penguin, science);
            Book book6 = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "9780307949486", 9.99, penguin, thriller);
            Book book7 = new Book("Gone Girl", "Gillian Flynn", "9780307588371", 10.99, simonSchuster, thriller);
            Book book8 = new Book("The Da Vinci Code", "Dan Brown", "9780307474278", 8.99, penguin, thriller);
            Book book9 = new Book("The Silence of the Lambs", "Thomas Harris", "9780312924584", 7.99, simonSchuster, thriller);
            Book book10 = new Book("Shutter Island", "Dennis Lehane", "9780061898815", 8.99, harperCollins, thriller);

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
            e.printStackTrace();
        }
    }
}
