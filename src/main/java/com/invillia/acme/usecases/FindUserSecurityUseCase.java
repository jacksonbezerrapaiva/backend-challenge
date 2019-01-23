package com.invillia.acme.usecases;

import com.invillia.acme.domain.UserSecurity;
import com.invillia.acme.repository.UserSecurityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class FindUserSecurityUseCase {

    private UserSecurityRepository userSecurityRepository;

    @Transactional(rollbackFor=Exception.class)
    public UserSecurity findUser(String username) {
        log.info("Begin FindUserSecurityUseCase : findUser : username : " + username);
        UserSecurity userSecurity = userSecurityRepository.findByUsername(username);
        log.info("End FindUserSecurityUseCase : findUser : username : " + username);
        return userSecurity;
    }
}
