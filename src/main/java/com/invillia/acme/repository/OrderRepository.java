package com.invillia.acme.repository;

import com.invillia.acme.domain.Order;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface OrderRepository extends Repository<Order, UUID> {

    Order save(Order Order);
    Order findByClientId(String clientId);
}
