package com.invillia.acme.repository;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.OrderItem;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface OrderItemRepository extends Repository<OrderItem, UUID> {

    OrderItem save(OrderItem orderItem);
    OrderItem findByClientId(String clientId);
}
