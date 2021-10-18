package com.trading.monolith.journal.repository;

import com.trading.monolith.journal.entity.TradeJournal;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeJournalRepository extends MongoRepository<TradeJournal, String>{
    
}
