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
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@Validated
public class OrderDto {

    @NotNull(message = "{validator.order.id.notnull}")
    private String id;

    @Valid
    private AddressDto address;

    @NotNull(message = "{validator.order.status.notnull}")
    private String status;

    @Valid
    private List<OrderItemDto> orderItemList;

    @NotNull(message = "{validator.order.confirmationDate.notnull}")
    private LocalDate confirmationDate;

    @NotNull(message = "{validator.order.cnpj.notnull}")
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
