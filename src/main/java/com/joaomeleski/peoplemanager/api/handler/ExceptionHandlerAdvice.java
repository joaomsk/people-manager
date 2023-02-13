package com.joaomeleski.peoplemanager.api.handler;

import com.joaomeleski.peoplemanager.domain.exception.MoreThanOneMainAddressException;
import com.joaomeleski.peoplemanager.domain.exception.ResourceNotFoundException;
import com.joaomeleski.peoplemanager.service.dto.out.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(MoreThanOneMainAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> notFoundResourceHandler(MoreThanOneMainAddressException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(buildError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> notFoundResourceHandler(NoSuchElementException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(buildError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> notFoundResourceHandler(ResourceNotFoundException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(buildError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    private ErrorDto buildError(String message) {
        return new ErrorDto(message, LocalDateTime.now());
    }
}
