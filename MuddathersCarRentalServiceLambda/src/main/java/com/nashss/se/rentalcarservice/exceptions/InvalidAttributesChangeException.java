package com.nashss.se.rentalcarservice.exceptions;

public class InvalidAttributesChangeException extends InvalidAttributesException{
    private static final long serialVersionUID = 456845456745674567L;
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

}
