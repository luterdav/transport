package hu.webuni.transport.luterdav.dto;

public class AddressSearchDto {
 
  private String countryCode;
  private String city;
  private String street;
  private String zipcode;
  
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
}

