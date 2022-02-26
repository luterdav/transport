package hu.webuni.transport.luterdav.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.transport.luterdav.dto.AddressSearchDto;
import hu.webuni.transport.luterdav.model.Address;
import hu.webuni.transport.luterdav.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public Optional<Address> findById(long id) {
		return addressRepository.findById(id);
	}

	@Transactional
	public Address save(Address address) {
		if (address.getId() != null)
			throw new IllegalArgumentException();
		return addressRepository.save(address);
	}

	@Transactional
	public Address update(Address newAddress, long id) {
		if (newAddress.getId() != null && newAddress.getId() != id)
			throw new IllegalArgumentException();
//		if(!addressRepository.existsById(id))
//			throw new NoSuchElementException();  //A verzió
		addressRepository.findById(id).get();   //B verzió
		newAddress.setId(id);
		return addressRepository.save(newAddress);
	}

	@Transactional
	public void delete(long id) {
		addressRepository.deleteById(id);
	}

	public Page<Address> findAddressByExample(AddressSearchDto example, Pageable page) {
		
		String city = example.getCity();
		String street = example.getStreet();
		String countryCode = example.getCountryCode();
		String zipcode = example.getZipcode();
		
		if (Stream.of(city, street, countryCode, zipcode )
				.allMatch(u -> u == null))
			throw new IllegalArgumentException();
		
		Specification<Address> spec = Specification.where(null);
		
		if (StringUtils.hasText(city))
			spec = spec.and(AddressSpecifications.hasCity(city));
		if (StringUtils.hasText(street))
			spec = spec.and(AddressSpecifications.hasStreet(street));
		if (StringUtils.hasText(countryCode))
			spec = spec.and(AddressSpecifications.hasCountryCode(countryCode));
		if (StringUtils.hasText(zipcode))
			spec = spec.and(AddressSpecifications.hasZipcode(zipcode));
		
		return this.addressRepository.findAll(spec, page);
	}
}
