package com.invillia.acme.repository;

import com.invillia.acme.domain.UserSecurity;
import org.springframework.data.repository.Repository;

import java.util.UUID;



public interface UserSecurityRepository extends Repository<UserSecurity, UUID> {
    UserSecurity findByUsername(String username);
}
