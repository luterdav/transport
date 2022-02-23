package hu.webuni.transport.luterdav.web;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.luterdav.dto.AddressDto;
import hu.webuni.transport.luterdav.dto.AddressSearchDto;
import hu.webuni.transport.luterdav.mapper.AddressMapper;
import hu.webuni.transport.luterdav.model.Address;
import hu.webuni.transport.luterdav.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;
	@Autowired
	AddressMapper addressMapper;

	@GetMapping
	public List<AddressDto> getAll() {
		return addressMapper.addressesToDtos(addressService.findAll());
	}

	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable long id) {
		Address address = addressService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return addressMapper.addressToDto(address);
	}

	@PostMapping
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		Address address;
		try {
			address = addressService.save(addressMapper.dtoToAddress(addressDto));
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return addressMapper.addressToDto(address);
	}

	@PostMapping("/search")
	public ResponseEntity<List<AddressDto>> findByExample(@RequestBody AddressSearchDto example,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
		Page<Address> addresses;
		List<AddressDto> responseBody;
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			addresses = addressService.findAddressByExample(example, page);
			responseHeaders.set("X-Total-Count", addresses.getTotalElements() + "");
			responseBody = addressMapper.addressesToDtos(addresses.getContent());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
	}

	@PutMapping("/{id}")
	public AddressDto updateAddress(@PathVariable long id, @RequestBody @Valid AddressDto addressDto) {
		Address address;
		try {
			address = addressService.update(addressMapper.dtoToAddress(addressDto), id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return addressMapper.addressToDto(address);
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressService.delete(id);
	}
}
