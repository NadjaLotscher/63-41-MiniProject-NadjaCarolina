package ch.hevs.businessobject;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String lastname;
	private String firstname;
    private String email;
    private String role; // "Library" or "Reader"

    
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Collection> collections;

    // Getters and Setters
    
   //Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

    //Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    //Address
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    //Role
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    //Collection
    public List<Collection> getCollections() {
        return collections;
    }
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }
    
    //Constructors
    public User() {}
    public User(String lastname, String firstname,String email,String role) {
    	this.lastname=lastname;
    	this.firstname=firstname;
    	this.email=email;
    	this.role=role;
    }
    
}

