package com.trading.monolith.journal.controller;

import java.util.Date;

import com.trading.monolith.journal.entity.AppUser;
import com.trading.monolith.journal.entity.ServerResponse;
import com.trading.monolith.journal.entity.TradeJournal;
import com.trading.monolith.journal.exception.InvalidTradeJournalException;
import com.trading.monolith.journal.exception.TradeJournalNotFoundException;
import com.trading.monolith.journal.exception.InvalidUserDataException;
import com.trading.monolith.journal.exception.UserNotFoundException;
import com.trading.monolith.journal.service.TradeJournalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user/journal")
public class TradeJournalController {
    
    @Autowired
    private TradeJournalService tradeJournalService;

    @PostMapping
    public ResponseEntity<ServerResponse> addJournal(@RequestAttribute("email") String email, @RequestBody TradeJournal tradeJournal) {
        ServerResponse response = new ServerResponse();
        response.setData(new Date());

        try{
            AppUser user = tradeJournalService.addTradeJournal(email, tradeJournal);
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(UserNotFoundException e){
            response.setMessage("User doesn't Exist with given email");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(InvalidUserDataException e){
            response.setMessage("Provided User Data is not valid");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(InvalidTradeJournalException e){
            response.setMessage("Provided Trade Journal Data is not valid");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{tradeJournalId}")
    public ResponseEntity<ServerResponse> deleteJournal(@RequestAttribute("email") String email, @PathVariable("tradeJournalId") String tradeJournalId) {
        ServerResponse response = new ServerResponse();
        response.setData(new Date());

        try{
            AppUser user = tradeJournalService.removeTradeJournal(email, tradeJournalId);
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(UserNotFoundException e){
            response.setMessage("User doesn't Exist with given email");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(InvalidUserDataException e){
            response.setMessage("Provided User Data is not valid");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(TradeJournalNotFoundException e){
            response.setMessage("Trade Journal Doesent Exist with given Id");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
