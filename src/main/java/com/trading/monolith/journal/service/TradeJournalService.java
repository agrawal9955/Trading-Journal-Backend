package com.trading.monolith.journal.service;

import com.trading.monolith.journal.entity.AppUser;
import com.trading.monolith.journal.entity.TradeJournal;
import com.trading.monolith.journal.exception.InvalidTradeJournalException;
import com.trading.monolith.journal.exception.InvalidUserDataException;
import com.trading.monolith.journal.exception.TradeJournalNotFoundException;
import com.trading.monolith.journal.exception.UserNotFoundException;
import com.trading.monolith.journal.repository.TradeJournalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeJournalService {
    
    @Autowired
    private TradeJournalRepository tradeJournalRepository;
    
    @Autowired
    private UserService userService;

    public AppUser addTradeJournal(String email, TradeJournal tradeJournal) throws UserNotFoundException, InvalidUserDataException, InvalidTradeJournalException{

        AppUser user = userService.getUser(email);
        // validate Trade Journal
        tradeJournal = tradeJournalRepository.save(tradeJournal);
        user.addTradeJournal(tradeJournal);

        userService.updateUser(user);
        return user;
    }

    public TradeJournal editTradeJournal(String email, TradeJournal tradeJournal) throws TradeJournalNotFoundException, InvalidTradeJournalException{
        // Validate Trade Journal Data 
        // Check if given user has editing rights for the given Trade Journal
        if(!tradeJournalRepository.existsById(tradeJournal.get_id())) throw new TradeJournalNotFoundException("No Trade Journal found with given id");
        TradeJournal updatedTradeJournal = tradeJournalRepository.save(tradeJournal);
        return updatedTradeJournal;
    }

    public AppUser removeTradeJournal(String email, String tradeJournalId) throws UserNotFoundException, InvalidUserDataException, TradeJournalNotFoundException{
        if(!tradeJournalRepository.existsById(tradeJournalId)) throw new TradeJournalNotFoundException("No Trade Journal found with given id");
        
        AppUser user = userService.getUser(email);
        TradeJournal tradeJournal = tradeJournalRepository.findById(tradeJournalId).get();
        user.removeTradeJournal(tradeJournal);

        tradeJournalRepository.deleteById(tradeJournalId);
        AppUser updatedUser = userService.updateUser(user);

        return updatedUser;
    }

}
