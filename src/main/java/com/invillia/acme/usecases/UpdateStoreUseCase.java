package com.invillia.acme.usecases;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDto;
import com.invillia.acme.exception.NotFoundStoreException;
import com.invillia.acme.repository.AddressRepository;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@AllArgsConstructor
public class UpdateStoreUseCase {

    private StoreRepository storeRepository;
    private AddressRepository addressRepository;
    private ValidationService validationService;

    @Transactional(rollbackFor = Exception.class)
    public void update(StoreDto storeDto) throws NotFoundStoreException {
        validationService.validate(storeDto);
        this.updateEntity(storeDto);
    }

    private void updateEntity(StoreDto storeDto) throws NotFoundStoreException {
        Store storeDb = storeRepository.findByCnpj(storeDto.getCnpj());
        if (storeDb == null) {
            throw new NotFoundStoreException();
        }
        Store store = new Store();
        Address address = new Address();
        address.setCity(storeDto.getAddress().getCity());
        address.setComplement(storeDto.getAddress().getComplement());
        address.setNeighborhood(storeDto.getAddress().getNeighborhood());
        address.setNumber(storeDto.getAddress().getNumber());
        address.setState(storeDto.getAddress().getState());
        address.setZipCode(storeDto.getAddress().getZipCode());
        address.setStreet(storeDto.getAddress().getStreet());
        address.setId(storeDb.getAddress().getId());
        address = addressRepository.save(address);
        store.setName(storeDto.getName());
        store.setAddress(address);
        store.setId(storeDb.getId());
        store.setCnpj(storeDto.getCnpj());
        storeRepository.save(store);
    }
}
