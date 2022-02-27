package hu.webuni.transport.luterdav.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.luterdav.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {

	@EntityGraph(attributePaths = {"section", "section.fromMilestone", "section.toMilestone"})
	List<TransportPlan> findAll();
	
	@EntityGraph(attributePaths = {"section", "section.fromMilestone", "section.toMilestone"})
	Optional<TransportPlan> findById(long id);
}
