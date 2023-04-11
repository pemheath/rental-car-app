package com.nashss.se.rentalcarservice.exceptions;

public class invalidAttributesValueException extends InvalidAttributesException {

    public invalidAttributesValueException() {
        super();
    }

    public invalidAttributesValueException(String message) {
        super(message);
    }

    public invalidAttributesValueException(Throwable cause) {
        super(cause);
    }

    public invalidAttributesValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public invalidAttributesValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
