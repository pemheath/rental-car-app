package com.nashss.se.rentalcarservice.exceptions;

public class invalidAttributesValueException extends InvalidAttributesException {
    private static final long serialVersionUID = 3456345634563456L;

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

}
