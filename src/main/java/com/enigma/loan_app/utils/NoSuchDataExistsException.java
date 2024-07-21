package com.enigma.loan_app.utils;

public class NoSuchDataExistsException extends RuntimeException {
    public NoSuchDataExistsException(String message) {
        super(message);
    }
}