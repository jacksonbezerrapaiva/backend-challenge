package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.StoreApi;
import com.invillia.acme.dto.AddressDto;
import com.invillia.acme.dto.StoreDto;
import com.invillia.acme.exception.NotFoundStoreException;
import com.invillia.acme.model.Store;
import com.invillia.acme.response.ConvertResponse;
import com.invillia.acme.usecases.FindStoreByCNPJUseCase;
import com.invillia.acme.usecases.SaveStoreUseCase;
import com.invillia.acme.usecases.UpdateStoreUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
@Api(value = "store", description = "Store API")
public class StoreApiController implements StoreApi {

    private SaveStoreUseCase saveStoreUseCase;
    private FindStoreByCNPJUseCase findStoreByCNPJUseCase;
    private UpdateStoreUseCase updateStoreUseCase;
    private ConvertResponse convertResponse;

    public ResponseEntity<?> newStore(@ApiParam(value = "StoreDto" ,required=true) @Valid @RequestBody Store store) {
        AddressDto addressDto = AddressDto.builder()
                .city(store.getAddress().getCity())
                .complement(store.getAddress().getComplement())
                .neighborhood(store.getAddress().getNeighborhood())
                .number(store.getAddress().getNumber())
                .state(store.getAddress().getState())
                .street(store.getAddress().getStreet())
                .zipCode(store.getAddress().getZipCode())
                .build();
        StoreDto storeDto = StoreDto.builder()
                .address(addressDto)
                .cnpj(store.getCnpj())
                .name(store.getName())
                .build();

        saveStoreUseCase.save(storeDto);
        return new ResponseEntity<>(convertResponse.newStore(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> findStoreByCNPJ(@ApiParam(value = "StoreDto" ,required=true) @Valid String cnpj) throws NotFoundStoreException {
        Store store = findStoreByCNPJUseCase.findByCNPJ(cnpj);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    public ResponseEntity<?> updateStore(@ApiParam(value = "CNPJ",required=true) @PathVariable("cnpj") String cnpj, @ApiParam(value = "Update store" ,required=true ) @Valid @RequestBody Store store) throws NotFoundStoreException {
        AddressDto addressDto = AddressDto.builder()
                .city(store.getAddress().getCity())
                .complement(store.getAddress().getComplement())
                .neighborhood(store.getAddress().getNeighborhood())
                .number(store.getAddress().getNumber())
                .state(store.getAddress().getState())
                .street(store.getAddress().getStreet())
                .zipCode(store.getAddress().getZipCode())
                .build();
        StoreDto storeDto = StoreDto.builder()
                .address(addressDto)
                .cnpj(store.getCnpj())
                .name(store.getName())
                .build();
        updateStoreUseCase.update(storeDto);
        return new ResponseEntity<>(convertResponse.updateStore(), HttpStatus.OK);
    }
}
