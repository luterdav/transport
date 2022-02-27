package hu.webuni.transport.luterdav.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.luterdav.dto.MilestoneDelayDto;
import hu.webuni.transport.luterdav.dto.TransportPlanDto;
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
	@PreAuthorize("hasAuthority('ROLE_TRANSPORTMANAGER')")
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

}
