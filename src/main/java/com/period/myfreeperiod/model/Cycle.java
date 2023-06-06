package com.period.myfreeperiod.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cycle {
    private int cycleLength;
    private int flowLength;
    private int lastPeriodDate;

}
