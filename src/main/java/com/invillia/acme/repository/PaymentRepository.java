package com.invillia.acme.repository;

import com.invillia.acme.domain.Payment;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface PaymentRepository extends Repository<Payment, UUID> {

    Payment save(Payment payment);
}
