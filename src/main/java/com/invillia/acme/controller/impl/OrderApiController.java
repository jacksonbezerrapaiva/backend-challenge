package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.OrderApi;
import com.invillia.acme.dto.AddressDto;
import com.invillia.acme.dto.OrderDto;
import com.invillia.acme.dto.OrderItemDto;
import com.invillia.acme.exception.CnpjNotFoundException;
import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.model.Order;
import com.invillia.acme.model.OrderItem;
import com.invillia.acme.response.ConvertResponse;
import com.invillia.acme.usecases.CreateOrderUseCase;
import com.invillia.acme.usecases.FindOrderUseCase;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@Api(value = "order", description = "order API")
public class OrderApiController implements OrderApi {

    private CreateOrderUseCase createOrderUseCase;
    private ConvertResponse convertResponse;
    private FindOrderUseCase findOrderUseCase;

    public ResponseEntity<?> newOrder(@ApiParam(value = "New OrderDto" ,required=true )  @Valid @RequestBody Order body) throws CnpjNotFoundException {
        AddressDto addressDto = AddressDto.builder().city(body.getAddress().getCity()).
                complement(body.getAddress().getComplement())
                .neighborhood(body.getAddress().getNeighborhood())
                .number(body.getAddress().getNumber())
                .state(body.getAddress().getState())
                .street(body.getAddress().getStreet())
                .zipCode(body.getAddress().getZipCode())
                .build();
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        for (OrderItem oi : body.getOrderItemList()) {

            OrderItemDto orderItemDto = OrderItemDto.builder()
                    .amount(oi.getAmount())
                    .description(oi.getDescription())
                    .id(oi.getId())
                    .unitPrice(oi.getUnitPrice()).build();
            orderItemDtoList.add(orderItemDto);
        }
        OrderDto orderDto = OrderDto.builder()
                .orderItemList(orderItemDtoList)
                .address(addressDto)
                .confirmationDate(body.getConfirmationDate())
                .status(body.getStatus())
                .cnpj(body.getCnpj())
                .id(body.getId())
                .build();
        createOrderUseCase.save(orderDto);
        return new ResponseEntity<>(convertResponse.newOrder(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> findOrderById(@ApiParam(value = "id",required=true) @PathVariable("id") String id) throws OrderNotFoundException {
        Order order = findOrderUseCase.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
