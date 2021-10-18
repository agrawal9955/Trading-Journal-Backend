package com.trading.monolith.journal.exception;

public class InvalidTradeJournalException extends Exception{
    
    public InvalidTradeJournalException(String message){
        super(message);
    }

    public InvalidTradeJournalException(String message, Throwable throwable){
        super(message, throwable);
    }
}
