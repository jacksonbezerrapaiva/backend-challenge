package com.invillia.acme.repository;

import com.invillia.acme.domain.Address;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface AddressRepository extends Repository<Address, UUID> {

    Address save(Address address);
}
