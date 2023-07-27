package org.example.transaction;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
public class Transaction {

    private String id;

    private String accountId;

    private String userId;

    private LocalDate date;

    private String description;

    private long amount;

}


