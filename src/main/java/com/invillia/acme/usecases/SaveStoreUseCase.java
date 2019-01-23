package com.invillia.acme.usecases;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDto;
import com.invillia.acme.repository.AddressRepository;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.ValidationService;
import com.invillia.acme.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@AllArgsConstructor
public class SaveStoreUseCase {

    private StoreRepository storeRepository;
    private AddressRepository addressRepository;
    private ValidationService validationService;

    @Transactional(rollbackFor = Exception.class)
    public void save(StoreDto storeDto) {
        validationService.validate(storeDto);
        this.saveEntity(storeDto);
    }

    private void saveEntity(StoreDto storeDto) {
        Store store = new Store();
        Address address = new Address();
        address.setCity(storeDto.getAddress().getCity());
        address.setComplement(storeDto.getAddress().getComplement());
        address.setNeighborhood(storeDto.getAddress().getNeighborhood());
        address.setNumber(storeDto.getAddress().getNumber());
        address.setState(storeDto.getAddress().getState());
        address.setZipCode(storeDto.getAddress().getZipCode());
        address.setStreet(storeDto.getAddress().getStreet());
        address = addressRepository.save(address);
        store.setName(storeDto.getName());
        Utils utils = new Utils();
        store.setCnpj(utils.sanitizeStr(storeDto.getCnpj()));
        store.setAddress(address);
        storeRepository.save(store);
    }
}
