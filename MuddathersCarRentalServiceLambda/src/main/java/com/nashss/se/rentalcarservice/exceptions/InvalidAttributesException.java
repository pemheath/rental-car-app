package com.nashss.se.rentalcarservice.exceptions;

import java.io.Serializable;

public class InvalidAttributesException extends RuntimeException {
    private static final long serialVersionUID = 1239871234563453L;

    public InvalidAttributesException() {
        super();
    }

    public InvalidAttributesException(String message) {
        super(message);
    }

    public InvalidAttributesException(Throwable cause) {
        super(cause);
    }

    public InvalidAttributesException(String message, Throwable cause) {
        super(message, cause);
    }

}
