package hu.webuni.transport.luterdav.service;

import hu.webuni.transport.luterdav.model.Address;
import hu.webuni.transport.luterdav.model.Address_;
import java.lang.invoke.SerializedLambda;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {
  public static Specification<Address> hasCity(String city) {
    return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), (city + "%").toLowerCase());
  }
  
  public static Specification<Address> hasStreet(String street) {
    return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), (street + "%").toLowerCase());
  }
  
  public static Specification<Address> hasCountryCode(String countryCode) {
    return (root, cq, cb) -> cb.equal(root.get(Address_.countryCode), countryCode);
  }
  
  public static Specification<Address> hasZipcode(String zipcode) {
    return (root, cq, cb) -> cb.equal(root.get(Address_.zipcode), zipcode);
  }
}

