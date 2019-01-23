package com.invillia.acme.annotations.impl;

import br.com.caelum.stella.ValidationMessage;
import com.invillia.acme.annotations.CNPJ;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CNPJValidator implements ConstraintValidator<CNPJ, String> {

    private String value;

    @Override
    public void initialize(CNPJ constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        br.com.caelum.stella.validation.CNPJValidator cnpjValidator = new br.com.caelum.stella.validation.CNPJValidator();
        List<ValidationMessage> messages = cnpjValidator.invalidMessagesFor(value);
        boolean retrieve = true;
        if (messages != null && !messages.isEmpty()) {
            retrieve = false;
        }
        return retrieve;
    }
}
