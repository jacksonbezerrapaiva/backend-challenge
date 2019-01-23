package com.invillia.acme.usecases;

import com.invillia.acme.model.Store;
import com.invillia.acme.exception.NotFoundStoreException;
import com.invillia.acme.model.Address;
import com.invillia.acme.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FindStoreByCNPJUseCase {

    StoreRepository storeRepository;

    public Store findByCNPJ(String cnpj) throws NotFoundStoreException {
        com.invillia.acme.domain.Store store = storeRepository.findByCnpj(cnpj);
        if (store == null) {
            throw new NotFoundStoreException();
        }
        Store storeDTO = new Store();
        storeDTO.setName(store.getName());
        storeDTO.setCnpj(store.getCnpj());
        Address address = new Address();
        address.setZipCode(store.getAddress().getZipCode());
        address.setNumber(store.getAddress().getNumber());
        address.setComplement(store.getAddress().getComplement());
        address.setStreet(store.getAddress().getStreet());
        address.setNeighborhood(store.getAddress().getNeighborhood());
        address.setCity(store.getAddress().getCity());
        address.setState(store.getAddress().getState());
        storeDTO.setAddress(address);
        return storeDTO;
    }
}
