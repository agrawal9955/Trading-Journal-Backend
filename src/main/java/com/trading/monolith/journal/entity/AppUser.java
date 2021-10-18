package com.trading.monolith.journal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.trading.monolith.journal.utility.RoleEnumeration;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="user")
public class AppUser {
    
    @Id
    private String _id;

    @Indexed(unique=true)
    private String email;
    private String name;
    private String phone;
    private String password;
    private String gender;
    private List<RoleEnumeration> roles = new ArrayList<>();
    
    @DBRef
    private List<TradeJournal> tradeJournals = new ArrayList<>();



    public void addRole(RoleEnumeration role){
        roles.add(role);
    }

    public void removeRole(RoleEnumeration role){
        roles.remove(role);
    }

    public void addTradeJournal(TradeJournal tradeJournal){
        tradeJournals.add(tradeJournal);
    }

    public void removeTradeJournal(TradeJournal tradeJournal){
        tradeJournals.remove(tradeJournal);
    }

}
