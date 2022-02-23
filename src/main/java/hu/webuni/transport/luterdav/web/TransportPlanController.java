package hu.webuni.transport.luterdav.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.luterdav.dto.MilestoneDelayDto;
import hu.webuni.transport.luterdav.dto.TransportPlanDto;
import hu.webuni.transport.luterdav.mapper.MilestoneMapper;
import hu.webuni.transport.luterdav.mapper.TransportPlanMapper;
import hu.webuni.transport.luterdav.model.TransportPlan;
import hu.webuni.transport.luterdav.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

	@Autowired
	TransportPlanService transportPlanService;
	@Autowired
	TransportPlanMapper transportPlanMapper;


	@GetMapping
	public List<TransportPlanDto> getAll() {
		return transportPlanMapper.transportPlansToDtos(transportPlanService.findAll());
	}

	@PostMapping("/{id}/delay")
	public TransportPlanDto addDelay(@PathVariable long id, @RequestBody MilestoneDelayDto milestoneDelayDto) {
		TransportPlan transportPlan;
		try {
			transportPlan = transportPlanService.incrementPlannedTimeWithDelay(id, milestoneDelayDto.getMilestoneId(),
					milestoneDelayDto.getDelay());
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return transportPlanMapper.transportPlanToDto(transportPlan);
	}

//	@GetMapping({ "/{id}" })
//	public AddressDto getById(@PathVariable long id) {
//		Address address = addressService.findById(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//		return addressMapper.addressToDto(address);
//	}

//	@PostMapping
//	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
//		Address address;
//		try {
//			address = addressService.save(addressMapper.dtoToAddress(addressDto));
//		} catch (IllegalArgumentException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//		}
//		return addressMapper.addressToDto(address);
//	}
//
//	private Sort.Direction getSortDirection(String direction) {
//		if (direction.equals("asc")) {
//			return Sort.Direction.ASC;
//		} else if (direction.equals("desc")) {
//			return Sort.Direction.DESC;
//		}
//		return Sort.Direction.ASC;
//	}
//
//	@PostMapping("/search")
//	public ResponseEntity<List<AddressDto>> findByExample(@RequestBody AddressSearchDto example,
//			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
//		Page<Address> addresses;
//		List<AddressDto> responseBody;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		try {
//			addresses = addressService.findAddressByExample(example, page);
//			responseHeaders.set("X-Total-Count", addresses.getTotalElements() + "");
//			responseBody = addressMapper.addressesToDtos(addresses.getContent());
//		} catch (IllegalArgumentException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//		}
//
//		return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
//	}
//
//	@PutMapping("/{id}")
//	public AddressDto updateAddress(@PathVariable long id, @RequestBody @Valid AddressDto addressDto) {
//		Address address;
//		try {
//			address = addressService.update(addressMapper.dtoToAddress(addressDto), id);
//		} catch (IllegalArgumentException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//		} catch (NoSuchElementException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
//		return addressMapper.addressToDto(address);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteAddress(@PathVariable long id) {
//		addressService.delete(id);
//	}
}
