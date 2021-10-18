package com.trading.monolith.journal.exception;

public class UserNotFoundException extends Exception{
    
    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

}
