package ch.hevs.businessobject;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
	
    // Primary Key
	@Id
	@Column(nullable = false, unique = true)
	private String isbn; //Id
	
	// Fields
    private String title;
    private String author;
	
    //relations
    @ManyToOne
    private Category category;

    @ManyToOne
    private Publisher publisher;
	
    @Column(nullable = false)
    private boolean available;
    
    // Getters and Setters
    
	//Isbn - Id
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn=isbn;
	}
	
	// Title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
		
	//Author
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
		
	//Category
	public Category getCategory() {
	    return category;
	   }
	public void setCategory(Category category) {
	    this.category = category;
	   }
	    
	 //Publisher
	 public Publisher getPublisher() {
	     return publisher;
	   }
	 public void setPublisher(Publisher publisher) {
	     this.publisher = publisher;
	   }
	 
	 // available
	 public boolean isAvailable() {
			return available;
		}
	 public void setAvailable(boolean available) {
			this.available = available;
		}
	
	//Constructors
	 public Book() {}
	 public Book(String title, String author, String isbn, Publisher publisher, Category category, boolean available) {
		    this.title = title;
		    this.author = author;
		    this.isbn = isbn;
		    this.publisher = publisher;
		    this.category = category;
		    this.available = available;
		}

	
	
}