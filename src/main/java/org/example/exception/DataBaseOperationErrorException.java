package org.example.exception;

public class DataBaseConnectionErrorException extends RuntimeException{
    public DataBaseConnectionErrorException(String message) {
        super(message);
    }
}
