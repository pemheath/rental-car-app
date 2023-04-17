package com.nashss.se.rentalcarservice.exceptions;



public class CarNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1239871232452345L;
    public CarNotFoundException() {super();}

    public CarNotFoundException(String message) {
        super(message);
    }
    public CarNotFoundException(Throwable cause) {
        super(cause);
    }

    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}