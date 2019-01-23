package com.invillia.acme.usecases;

import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.model.Address;
import com.invillia.acme.model.Order;
import com.invillia.acme.model.OrderItem;
import com.invillia.acme.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class FindOrderUseCase {

    private OrderRepository orderRepository;

    public Order findById(String id) throws OrderNotFoundException {
        com.invillia.acme.domain.Order order = orderRepository.findByClientId(id);
        Order o = new Order();
        if (order == null) {
            throw new OrderNotFoundException();
        }
        List<OrderItem> ol = new ArrayList<>();
        if (order.getOrderItemList() != null) {
            for (com.invillia.acme.domain.OrderItem oi : order.getOrderItemList()) {
                OrderItem oiModel = new OrderItem();
                oiModel.setAmount(oi.getAmount());
                oiModel.setDescription(oi.getDescription());
                oiModel.setUnitPrice(oi.getUnitPrice());
                oiModel.setId(oi.getClientId());
                ol.add(oiModel);
            }
        }

        Address address = new Address();
        address.setCity(order.getAddress().getCity());
        address.setComplement(order.getAddress().getComplement());
        address.setNeighborhood(order.getAddress().getNeighborhood());
        address.setNumber(order.getAddress().getNumber());
        address.setState(order.getAddress().getState());
        address.setZipCode(order.getAddress().getZipCode());
        address.setStreet(order.getAddress().getStreet());
        o.setOrderItemList(ol);
        o.setConfirmationDate(order.getConfirmationDate());
        o.setStatus(order.getStatus());
        o.setAddress(address);
        o.setId(order.getClientId());
        o.setCnpj(order.getStore().getCnpj());
        return o;
    }
}
