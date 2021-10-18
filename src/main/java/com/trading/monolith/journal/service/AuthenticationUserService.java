package com.trading.monolith.journal.service;

import java.util.ArrayList;

import com.trading.monolith.journal.entity.AppUser;
import com.trading.monolith.journal.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            AppUser user = userService.getUser(username);
            if(user == null) throw new UsernameNotFoundException("User doesn't exist with email: " + username);
            // Collection<? extends GrantedAuthority> roles = user.getRoles();
            return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
        }catch(UserNotFoundException e){
            throw new UsernameNotFoundException("User doesn't exist with email: " + username);
        }
    }
    
}
