package com.trading.monolith.journal.repository;

import com.trading.monolith.journal.entity.AppUser;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AppUser, ObjectId>{
    public AppUser findByEmail(String email);
}
