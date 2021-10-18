package com.trading.monolith.journal.service;

import com.trading.monolith.journal.entity.AuthenticationRequest;
import com.trading.monolith.journal.utility.JWTUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {
    
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationUserService authenticationUserService;


    public String authenticateUser(AuthenticationRequest authRequest){

        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()   
                )
            );
        }catch(BadCredentialsException e){
            log.error("Invalid Credentials Provided", e);      
            return null;
        }

        final UserDetails userDetails
                     = authenticationUserService.loadUserByUsername(authRequest.getEmail());

        final String token
                     = jwtUtility.generateToken(userDetails);

        return token;
    }



}
