package com.joaomeleski.peoplemanager.domain.exception;

public class MoreThanOneMainAddressException extends RuntimeException {
    public MoreThanOneMainAddressException(String message) {
        super(message);
    }
}
