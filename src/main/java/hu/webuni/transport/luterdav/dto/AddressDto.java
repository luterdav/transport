package hu.webuni.transport.luterdav.dto;

import javax.validation.constraints.NotEmpty;

public class AddressDto {

  private Long id;
  @NotEmpty
  private String countryCode;
  @NotEmpty
  private String city;
  @NotEmpty
  private String street;
  @NotEmpty
  private String zipcode;
  @NotEmpty
  private String houseNumber;
  private double latitude;
  private double longitude;
  
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
