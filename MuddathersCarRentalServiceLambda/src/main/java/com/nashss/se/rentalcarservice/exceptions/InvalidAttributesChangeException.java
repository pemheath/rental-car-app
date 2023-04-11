package com.nashss.se.rentalcarservice.exceptions;

public class InvalidAttributesChangeException extends InvalidAttributesException{

    public InvalidAttributesChangeException() {
        super();
    }

    public InvalidAttributesChangeException(String message) {
        super(message);
    }

    public InvalidAttributesChangeException(Throwable cause) {
        super(cause);
    }

    public InvalidAttributesChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAttributesChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
