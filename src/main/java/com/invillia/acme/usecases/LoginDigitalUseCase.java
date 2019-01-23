package com.invillia.acme.usecases;

import com.invillia.acme.dto.LoginDto;
import com.invillia.acme.model.Login;
import com.invillia.acme.service.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Component
@Slf4j
@AllArgsConstructor
public class LoginDigitalUseCase {

    private ValidationService validationService;

    public void validate(@Valid LoginDto loginDTO) throws ConstraintViolationException {
        log.info("Begin LoginDigitalUseCase : validate : email" + loginDTO.getUsername() + " : password: " + loginDTO.getPassword());
        validationService.validate(loginDTO);
        log.info("End LoginDigitalUseCase : validate : email" + loginDTO.getUsername() + " : password: " + loginDTO.getPassword());
    }
}
