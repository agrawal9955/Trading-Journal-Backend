package com.trading.monolith.journal.utility;

import com.trading.monolith.journal.entity.AppUser;

import org.springframework.stereotype.Component;

@Component
public class UserValidationUtility {
    
    public UserValidationUtility(){}


    // Validate if email and password are correct
    public boolean validateNewUser(AppUser user){
        if(user.getName() == null) return false;
        if(user.getEmail() == null) return false;
        if(user.getGender() == null) return false;
        if(user.getPassword() == null) return false;
        if(user.getPhone() == null) return false;

        return true;
    }
}
