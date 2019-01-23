package com.invillia.acme.exception_handle;

import com.invillia.acme.exception.*;
import com.invillia.acme.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(ConstraintViolationException ex) {
        logger.warn(ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            exceptionResponse.addError(constraintViolation.getMessage());
        }
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundStoreException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(NotFoundStoreException ex) {
        logger.warn(ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        exceptionResponse.addError(messageSource().getMessage("validator.store.not.found", null, null));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(OrderNotFoundException ex) {
        logger.warn(ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        exceptionResponse.addError(messageSource().getMessage("validator.order.not.found", null, null));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IntervalNotPermitException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(IntervalNotPermitException ex) {
        logger.warn(ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        exceptionResponse.addError(messageSource().getMessage("validator.internal.not.permit", null, null));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(OrderItemNotFoundException ex) {
        logger.warn(ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        exceptionResponse.addError(messageSource().getMessage("validator.order.item.not.found", null, null));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CnpjNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(CnpjNotFoundException ex) {
        logger.warn(ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        exceptionResponse.addError(messageSource().getMessage("validator.cnpj.not.found", null, null));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}