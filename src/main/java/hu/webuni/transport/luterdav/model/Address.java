package hu.webuni.transport.luterdav.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String countryCode;
  private String city;
  private String street;
  private String zipcode;
  private String houseNumber;
  private double latitude;
  private double longitude;
  
//  @OneToMany(mappedBy = "address")
//  private List<Milestone> milestones;
  
  public Address() {}
  
  public Address(Long id, String countryCode, String city, String street, String zipcode, String houseNumber,
		double latitude, double longitude) {
	super();
	this.id = id;
	this.countryCode = countryCode;
	this.city = city;
	this.street = street;
	this.zipcode = zipcode;
	this.houseNumber = houseNumber;
	this.latitude = latitude;
	this.longitude = longitude;
}


public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getCountryCode() {
    return this.countryCode;
  }
  
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  
  public String getCity() {
    return this.city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getStreet() {
    return this.street;
  }
  
  public void setStreet(String street) {
    this.street = street;
  }
  
  public String getZipcode() {
    return this.zipcode;
  }
  
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  
  public String getHouseNumber() {
    return this.houseNumber;
  }
  
  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }
  
  public double getLatitude() {
    return this.latitude;
  }
  
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }
  
  public double getLongitude() {
    return this.longitude;
  }
  
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
  
}

