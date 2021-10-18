package com.trading.monolith.journal.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerResponse {
    
    private Date timeStamp;
    private Integer statusCode;
    private String status;
    private String message;
    private String developerMessage;
    private Object data;
}
