package com.invillia.acme.dto;

import com.invillia.acme.annotations.CNPJ;
import com.invillia.acme.utils.Utils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@Getter
@Setter
@Builder
public class StoreDto {

    @NotNull(message = "{validator.store.name.notnull}")
    @Size(min=1,max=128, message = "{validator.store.name.min.max}")
    private String name;

    @Valid
    private AddressDto address;

    @NotNull(message = "{validator.store.cnpj.notnull}")
    @Size(min=14,max=18, message = "{validator.store.cnpj.min.max}")
    @CNPJ
    private String cnpj;

    public String getCnpj() {
        if (this.cnpj != null && !this.cnpj.equals("")) {
            Utils utils = new Utils();
            this.cnpj = utils.sanitizeStr(this.cnpj);
        }
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
