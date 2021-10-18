package com.trading.monolith.journal.controller;

import java.util.Date;

import com.trading.monolith.journal.entity.AppUser;
import com.trading.monolith.journal.entity.ServerResponse;
import com.trading.monolith.journal.exception.UserNotFoundException;
import com.trading.monolith.journal.exception.InvalidUserDataException;
import com.trading.monolith.journal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ServerResponse> getUser(@RequestAttribute("email") String email){
        ServerResponse response = new ServerResponse();
        response.setData(new Date());

        try{
            AppUser user = userService.getUserHidePassword(email);
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(UserNotFoundException e){
            response.setMessage("User doesn't Exist with given email");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ServerResponse> updateUser(@RequestAttribute("email") String email, @RequestBody AppUser user){
        ServerResponse response = new ServerResponse();
        response.setData(new Date());
        
        try{
            AppUser updatedUser = userService.updateUser(user);
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(UserNotFoundException e){
            response.setMessage("User doesn't Exist with given email");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(InvalidUserDataException e){
            response.setMessage("Provided User Data is not valid");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
