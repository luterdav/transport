package hu.webuni.transport.luterdav.mapper;

import hu.webuni.transport.luterdav.dto.AddressDto;
import hu.webuni.transport.luterdav.model.Address;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
  List<AddressDto> addressesToDtos(List<Address> addresses);
  
  AddressDto addressToDto(Address address);
  
  Address dtoToAddress(AddressDto addressDto);
  
  List<Address> dtosToAddresses(List<AddressDto> addressDtos);
}
