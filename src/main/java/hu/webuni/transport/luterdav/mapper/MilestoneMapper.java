package hu.webuni.transport.luterdav.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.transport.luterdav.dto.MilestoneDto;
import hu.webuni.transport.luterdav.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {
	
  List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
  
  MilestoneDto milestoneToDto(Milestone milestone);
  
  Milestone dtoToMilestone(MilestoneDto milestoneDto);
  
  List<Milestone> dtosToMilestonees(List<MilestoneDto> milestoneDtos);
}
