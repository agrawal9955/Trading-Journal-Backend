package com.trading.monolith.journal.controller;

import java.util.Date;

import com.trading.monolith.journal.entity.AppUser;
import com.trading.monolith.journal.entity.AuthenticationRequest;
import com.trading.monolith.journal.entity.ServerResponse;
import com.trading.monolith.journal.exception.InvalidUserDataException;
import com.trading.monolith.journal.exception.UserAlreadyExistException;
import com.trading.monolith.journal.service.AuthenticationService;
import com.trading.monolith.journal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService autheticationService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ServerResponse> login(@RequestBody AuthenticationRequest authReq){
        ServerResponse response = new ServerResponse();
        response.setData(new Date());
        
        if(authReq.getEmail() == null){
            response.setMessage("Email field is null");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else if(authReq.getPassword() == null){
            response.setMessage("Password field is null");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String token = autheticationService.authenticateUser(authReq);

        if(token == null) {
            response.setMessage("Invalid Credentials provided");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<ServerResponse> register(@RequestBody AppUser user){
        ServerResponse response = new ServerResponse();
        response.setData(new Date());

        try{
            AppUser createdUser = userService.addUser(user);
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setData(createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(InvalidUserDataException e){
            response.setMessage("Provided User Data is not valid");
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch(UserAlreadyExistException e){
            response.setMessage("User Already exist with given email");
            response.setStatusCode(HttpStatus.IM_USED.value());
            return new ResponseEntity<>(response, HttpStatus.IM_USED);
        }
    }

}
