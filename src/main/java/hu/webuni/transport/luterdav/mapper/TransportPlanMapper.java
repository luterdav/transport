package hu.webuni.transport.luterdav.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.transport.luterdav.dto.TransportPlanDto;
import hu.webuni.transport.luterdav.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {
	
  List<TransportPlanDto> transportPlansToDtos(List<TransportPlan> transportPlans);
  
  TransportPlanDto transportPlanToDto(TransportPlan transportPlan);
  
  TransportPlan dtoToTransportPlan(TransportPlanDto transportPlanDto);
  
  List<TransportPlan> dtosToTransportPlans(List<TransportPlanDto> transportPlanDtos);
}
