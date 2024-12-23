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
    private String lastname;
	private String firstname;

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
    //Firstname
   	public String getFirstname() {
   		return firstname;
   	}
   	public void setFirstname(String firstname) {
   		this.firstname = firstname;
   	}
   	
   	//Lastname
   	public String getLastname() {
   		return lastname;
   	}
   	public void setLastname(String lastname) {
   		this.lastname = lastname;
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
}
