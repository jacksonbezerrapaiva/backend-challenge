package com.invillia.acme.repository;

import com.invillia.acme.domain.Store;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface StoreRepository extends Repository<Store, UUID> {

    void save(Store store);

    Store findByCnpj(String cnpj);
}
