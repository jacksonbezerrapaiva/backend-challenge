package com.invillia.acme.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@Validated
public class AddressDto {

    @NotNull(message = "{validator.address.zipcode.notnull}")
    @Size(min=8,max=8, message = "{validator.address.zipcode.max}")
    private String zipCode;

    @NotNull(message = "{validator.address.number.notnull}")
    @Size(min=1,max=15, message = "{validator.address.number.min.max}")
    private String number;

    @Size(min=0,max=50, message = "{validator.address.complement.min.max}")
    private String complement;

    @NotNull(message = "{validator.address.public_place.notnull}")
    @Size(min=1,max=100, message = "{validator.address.street.min.max}")
    private String street;

    @NotNull(message = "{validator.address.neighborhood.notnull}")
    @Size(min=1,max=50, message = "{validator.address.neighborhood.min.max}")
    private String neighborhood;

    @NotNull(message = "{validator.address.city.notnull}")
    @Size(min=1,max=40, message = "{validator.address.city.min.max}")
    private String city;

    @NotNull(message = "{validator.address.state.notnull}")
    @Size(min=2,max=2, message = "{validator.address.state.min.max}")
    private String state;

}
