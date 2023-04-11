package com.nashss.se.rentalcarservice.exceptions;

public class InvalidAttributesException extends RuntimeException {

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


    public InvalidAttributesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
