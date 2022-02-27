package hu.webuni.transport.luterdav.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.luterdav.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
	
	Optional<Section> findByTransportPlanIdAndFromMilestoneIdOrToMilestoneId(long transportPlanId, long milestoneId1, long milestoneId2);
	
	Optional<Section> findByNumberAndTransportPlanId(int number, Long transportId);
	
}
