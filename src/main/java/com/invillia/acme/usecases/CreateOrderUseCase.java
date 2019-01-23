package com.invillia.acme.usecases;

import com.invillia.acme.annotations.CNPJ;
import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.OrderDto;
import com.invillia.acme.dto.OrderItemDto;
import com.invillia.acme.exception.CnpjNotFoundException;
import com.invillia.acme.repository.AddressRepository;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Slf4j
public class CreateOrderUseCase {

    private AddressRepository addressRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private StoreRepository storeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(OrderDto orderDto) throws CnpjNotFoundException {
        Order order = new Order();
        Address address = new Address();
        address.setCity(orderDto.getAddress().getCity());
        address.setComplement(orderDto.getAddress().getComplement());
        address.setNeighborhood(orderDto.getAddress().getNeighborhood());
        address.setNumber(orderDto.getAddress().getNumber());
        address.setState(orderDto.getAddress().getState());
        address.setZipCode(orderDto.getAddress().getZipCode());
        address.setStreet(orderDto.getAddress().getStreet());
        address = addressRepository.save(address);
        order.setAddress(address);
        order.setConfirmationDate(orderDto.getConfirmationDate());
        order.setStatus(orderDto.getStatus());
        order.setClientId(orderDto.getId());

        Store store = storeRepository.findByCnpj(orderDto.getCnpj());
        if (store == null) {
            throw new CnpjNotFoundException();
        }
        order.setStore(store);
        order = orderRepository.save(order);
        for(OrderItemDto oiDto : orderDto.getOrderItemList()) {
            OrderItem oi = new OrderItem();
            oi.setClientId(oiDto.getId());
            oi.setAmount(oiDto.getAmount());
            oi.setOrder(order);
            oi.setDescription(oiDto.getDescription());
            oi.setUnitPrice(oiDto.getUnitPrice());
            orderItemRepository.save(oi);
        }
    }
}


