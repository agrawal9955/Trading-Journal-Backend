package com.trading.monolith.journal.exception;

public class InvalidUserDataException extends Exception{
    
    public InvalidUserDataException(String message){
        super(message);
    }

    public InvalidUserDataException(String message, Throwable throwable){
        super(message, throwable);
    }
}
