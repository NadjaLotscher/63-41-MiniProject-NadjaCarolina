package ch.hevs.businessobject;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String street;
    private String city;
    private String postalCode;
    private String country;

    
    // Getters and Setters
    
    //Street
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    //City
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    //Postal Code
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    //Country
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    
}

