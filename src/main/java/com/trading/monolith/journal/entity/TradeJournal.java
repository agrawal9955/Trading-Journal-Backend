package com.trading.monolith.journal.entity;

import javax.persistence.Id;

import com.trading.monolith.journal.utility.TradeTypeEnumeration;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="trade_journal")
public class TradeJournal {
    
    @Id
    private String _id;
    private String dateTime;
    private TradeTypeEnumeration tradeType; // long, short
    private String stock;
    private Double entry;
    private Integer volume; // number os stocks
    private Double stoploss;
    private Double plannedExit; 
    private String imageUrl;
    private String tradeDuration; // Intraday, Swing, Investment
    private String psychology;
    private Double tradeCost; // for personal preference purpose
    private Double exit;
    private Integer rating; // calculated by business logic
}
