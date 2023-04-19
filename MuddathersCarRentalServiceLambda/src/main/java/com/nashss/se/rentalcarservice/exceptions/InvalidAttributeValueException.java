package com.nashss.se.rentalcarservice.exceptions;

public class InvalidAttributeValueException extends InvalidAttributesException {
    private static final long serialVersionUID = 3456345634563456L;

    public InvalidAttributeValueException() {
        super();
    }

    public InvalidAttributeValueException(String message) {
        super(message);
    }

    public InvalidAttributeValueException(Throwable cause) {
        super(cause);
    }

    public InvalidAttributeValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
