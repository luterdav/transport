package hu.webuni.transport.luterdav.service;

import hu.webuni.transport.luterdav.model.Address;
import hu.webuni.transport.luterdav.model.Milestone;
import hu.webuni.transport.luterdav.model.Section;
import hu.webuni.transport.luterdav.model.TransportPlan;
import hu.webuni.transport.luterdav.repositories.AddressRepository;
import hu.webuni.transport.luterdav.repositories.MilestoneRepository;
import hu.webuni.transport.luterdav.repositories.SectionRepository;
import hu.webuni.transport.luterdav.repositories.TransportPlanRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitDbService {
  @Autowired
  TransportPlanRepository transportPlanRepository;
  
  @Autowired
  SectionRepository sectionRepository;
  
  @Autowired
  MilestoneRepository milestoneRepository;
  
  @Autowired
  AddressRepository addressRepository;
  
  public void init() {
    
	Address address1 = addressRepository.save(new Address(null, "HU", "Budapest", "Garay", "1182", "4", 47.50065, 19.07926));
    Address address2 = addressRepository.save(new Address(null, "AT", "Bécs", "Gluckgasse", "1010", "5", 48.20550, 16.36936));
    Address address3 = addressRepository.save(new Address(null, "AT", "Graz", "Karlauergürtel", "8020", "24", 47.05745, 15.43094));
    
   
    Milestone milestone1 = milestoneRepository.save(new Milestone(null, address1, LocalDateTime.of(2022,3,10,10,10,10)));
    Milestone milestone2 = milestoneRepository.save(new Milestone(null, address2, LocalDateTime.of(2022,3,20,2,2,2)));
    Milestone milestone3 = milestoneRepository.save(new Milestone(null, address2, LocalDateTime.of(2022,3,21,3,3,3)));
    Milestone milestone4 = milestoneRepository.save(new Milestone(null, address3, LocalDateTime.of(2022,3,22,4,4,4)));
    Milestone milestone5 = milestoneRepository.save(new Milestone(null, address3, LocalDateTime.of(2022,4,20,4,4,4)));
    
    TransportPlan transportPlan1 = transportPlanRepository.save(new TransportPlan(null, 10000, null));
    
    Section section1 = sectionRepository.save(new Section(null, milestone1, milestone2, 0, transportPlan1));
    Section section2 = sectionRepository.save(new Section(null, milestone3, milestone4, 1, transportPlan1));
    
    
    
  }
}
