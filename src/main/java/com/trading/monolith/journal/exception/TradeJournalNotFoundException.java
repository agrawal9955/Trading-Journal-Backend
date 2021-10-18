package com.trading.monolith.journal.exception;

public class TradeJournalNotFoundException extends Exception{
    
    public TradeJournalNotFoundException(String message){
        super(message);
    }

    public TradeJournalNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
