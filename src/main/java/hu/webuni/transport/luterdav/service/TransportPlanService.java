package hu.webuni.transport.luterdav.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.luterdav.config.TransportConfigProperties;
import hu.webuni.transport.luterdav.model.Milestone;
import hu.webuni.transport.luterdav.model.Section;
import hu.webuni.transport.luterdav.model.TransportPlan;
import hu.webuni.transport.luterdav.repositories.MilestoneRepository;
import hu.webuni.transport.luterdav.repositories.SectionRepository;
import hu.webuni.transport.luterdav.repositories.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportPlanRepository;
	@Autowired
	MilestoneRepository milestoneRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	TransportConfigProperties config;

	public List<TransportPlan> findAll() {
		return transportPlanRepository.findAll();
	}

	public Optional<TransportPlan> findById(long id) {
		return transportPlanRepository.findById(id);
	}

	@Transactional
	public TransportPlan incrementPlannedTimeWithDelay(long transportPlanId, long milestoneId, long delay) {
		TransportPlan transportPlan = findById(transportPlanId).get();
		transportPlan.setRevenue(revenueDecrease(transportPlan.getRevenue(), delay));

		Milestone milestone = milestoneRepository.findById(milestoneId).get();
		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));

		Section section = sectionRepository
				.findByTransportPlanIdAndFromMilestoneIdOrToMilestoneId(transportPlanId, milestoneId, milestoneId)
				.orElseThrow(IllegalArgumentException::new);

		if (section.getFromMilestone().getId().equals(milestoneId)) {
			section.getToMilestone().setPlannedTime(section.getToMilestone().getPlannedTime().plusMinutes(delay));
		} else {
			Section sectionNext = sectionRepository
					.findByNumberAndTransportPlanId(section.getNumber() + 1, transportPlanId)
					.get();
			sectionNext.getFromMilestone().setPlannedTime(sectionNext.getFromMilestone().getPlannedTime().plusMinutes(delay));
		}
		
		return transportPlan;
	}
	
	public int revenueDecrease(int revenue, long delay) {
		TreeMap<Long, Integer> limits = config.getDecrease().getLimits();
		Entry<Long, Integer> floorEntry = limits.floorEntry(delay);
		
		return revenue * (100 - floorEntry.getValue()) / 100;
	}

//	@Transactional
//	public Address save(Address address) {
//		if (address.getId() != null)
//			throw new IllegalArgumentException();
//		return addressRepository.save(address);
//	}

//	@Transactional
//	public Address update(Address newAddress, long id) {
//		if (newAddress.getId() != null && newAddress.getId() != id)
//			throw new IllegalArgumentException();
//		addressRepository.findById(id).get();
//		newAddress.setId(id);
//		return addressRepository.save(newAddress);
//	}
//
//	@Transactional
//	public void delete(long id) {
//		addressRepository.deleteById(id);
//	}

}
