package com.ua.tugboats.exceptions;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
