package com.trading.monolith.journal.service;

import com.trading.monolith.journal.entity.AppUser;
import com.trading.monolith.journal.exception.InvalidUserDataException;
import com.trading.monolith.journal.exception.UserAlreadyExistException;
import com.trading.monolith.journal.exception.UserNotFoundException;
import com.trading.monolith.journal.repository.UserRepository;
import com.trading.monolith.journal.utility.RoleEnumeration;
import com.trading.monolith.journal.utility.UserValidationUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private UserValidationUtility userValidationUtility = new UserValidationUtility();

    public AppUser getUserHidePassword(String email) throws UserNotFoundException{
        AppUser user;
        try{
            user = userRepository.findByEmail(email);
            user.setPassword("");
            return user;
        } catch(Exception e){
            log.error("Invalid Email for user with email: " + email);
            throw new UserNotFoundException("User Not Found with Email");
        }
    }

    public AppUser getUser(String email) throws UserNotFoundException{
        AppUser user;
        try{
            user = userRepository.findByEmail(email);
            return user;
        } catch(Exception e){
            log.error("Invalid Email for user with email: " + email);
            throw new UserNotFoundException("User Not Found with Email");
        }
    }

    public AppUser updateUser(AppUser user) throws UserNotFoundException, InvalidUserDataException{
        getUser(user.getEmail());
        return userRepository.save(user);
    }

    public AppUser addUser(AppUser user) throws InvalidUserDataException, UserAlreadyExistException{
        if(userRepository.findByEmail(user.getEmail()) != null) throw new UserAlreadyExistException("User Already Exist with Given Email");
        if(!userValidationUtility.validateNewUser(user)) throw new InvalidUserDataException("User details are not correct");

        user.addRole(RoleEnumeration.USER);
        AppUser createdUser = userRepository.save(user);

        createdUser.setPassword("");
        return createdUser;
    }

}
