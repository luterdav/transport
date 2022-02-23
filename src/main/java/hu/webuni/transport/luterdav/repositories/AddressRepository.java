package hu.webuni.transport.luterdav.repositories;

import hu.webuni.transport.luterdav.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {}

