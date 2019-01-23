package com.invillia.acme.repository;

import com.invillia.acme.domain.Refund;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface RefundRepository extends Repository<Refund, UUID> {

    Refund save(Refund refund);
}
