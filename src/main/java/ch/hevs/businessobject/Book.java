package ch.hevs.businessobject;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	private String isbn; //Id
	
    private String title;
    private String author;
    private double price;
	
    //relations
    @ManyToOne
    private Category category;

    @ManyToOne
    private Publisher publisher;
	
    
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
		
	//Price
	public double getPrice() {
	    return price;
	   }
	public void setPrice(double price) {
	    this.price = price;
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
	
	
}