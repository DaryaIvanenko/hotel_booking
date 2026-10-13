package org.example.exception;

public class RooomAlreadyBookedException extends RuntimeException{
    public RooomAlreadyBookedException(String message) {
        super(message);
    }
}
