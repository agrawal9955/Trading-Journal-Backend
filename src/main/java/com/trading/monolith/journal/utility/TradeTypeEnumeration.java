package com.trading.monolith.journal.utility;

public enum TradeTypeEnumeration {
    LONG("long"), SHORT("short");

    private String tradeType;

    TradeTypeEnumeration(String tradeType){
        this.tradeType = tradeType;
    }

    public String getRole() {
        return tradeType;
    }

    public void setRole(String tradeType) {
        this.tradeType = tradeType;
    }
}
