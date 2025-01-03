package ch.hevs.test;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Test;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;

import ch.hevs.businessobject.Book;
import ch.hevs.businessobject.User;
import ch.hevs.businessobject.Publisher;
import ch.hevs.businessobject.Category;
import ch.hevs.businessobject.Collection;

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
			
			/*
			//Banque part :
		
			Client c1 = new Client("Zinedine", "Zidane");
			Account a1 = new Account("1000", 10000, c1, "Compte Courant");
			
			Client c2 = new Client("Michel", "Platini");
			Account a2 = new Account("1001", 20000, c2, "Compte Courant");
			Account a3 = new Account("1003", 1000, c2, "Livret A");
	
			Client c3 = new Client("Jean-Pierre", "Papin");
			Account a4 = new Account("1002", 30000, c3, "Compte Courant");
	
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			
			em.persist(a1);
			em.persist(a2);
			em.persist(a3);
			em.persist(a4);
			tx.commit();
			*/
			
			// Create Categories
            Category fiction = new Category ("Fiction");
            Category fantasy = new Category("Fantasy");
            Category science = new Category("Science");
            Category thriller = new Category("Thriller");
            em.persist(fiction);
            em.persist(fantasy);
            em.persist(science);
            em.persist(thriller);
			
            //Publishers
            Publisher scholastic = new Publisher("Scholastic");
            Publisher penguin = new Publisher("Penguin Random House");
            Publisher harperCollins = new Publisher("HarperCollins");
            Publisher simonSchuster = new Publisher("Simon & Schuster");
            em.persist(scholastic);
            em.persist(penguin);
            em.persist(harperCollins);
            em.persist(simonSchuster);
            
            //Books
            Book book1 = new Book("The Hunger Games", "Suzanne Collins", "978-1407132082", 11.47, scholastic, fiction);
            Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "978-0747532743", 12.99, scholastic, fantasy);
            Book book3 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0261102385", 15.99, harperCollins, fantasy);
            Book book4 = new Book("A Brief History of Time", "Stephen Hawking", "978-0553380163", 10.99, penguin, science);
            Book book5 = new Book("Dune", "Frank Herbert", "978-0441013593", 9.99, penguin, science);
            Book book6 = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "978-0307949486", 9.99, penguin, thriller);
            Book book7 = new Book("Gone Girl", "Gillian Flynn", "978-0307588371", 10.99, simonSchuster, thriller);
            Book book8 = new Book("The Da Vinci Code", "Dan Brown", "978-0307474278", 8.99, penguin, thriller);
            Book book9 = new Book("The Silence of the Lambs", "Thomas Harris", "978-0312924584", 7.99, simonSchuster, thriller);
            Book book10 = new Book("Shutter Island", "Dennis Lehane", "978-0061898815", 8.99, harperCollins, thriller);
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
            
            //Users
            User user1 = new User("Alice", "Smith", "alice.smith@employeLibrary.com", "Library");
            User user2 = new User("Bob", "Johnson", "bob.johnson@readerLibrary.com", "Reader");
            User user3 = new User("Charlie", "Brown", "charlie.brown@employeLibrary.com", "Library");
            em.persist(user1);
            em.persist(user2);
            em.persist(user3);
            
            //Collections
            
            
            // Commit the transaction
            tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
