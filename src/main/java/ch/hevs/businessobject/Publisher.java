package ch.hevs.businessobject;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Publisher implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Address address;
    

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> books;

    // Getters and Setters
    
    //Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //Name
   	public String getName() {
   		return name;
   	}
   	public void setName(String name) {
   		this.name = name;
   	}
   	
    //Address
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    //Book
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    //Constructors
    public Publisher() {}
    public Publisher(String name) {
    	this.name=name;	
    }
    
}
