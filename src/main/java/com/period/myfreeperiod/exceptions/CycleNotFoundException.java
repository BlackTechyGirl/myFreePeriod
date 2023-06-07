package com.period.myfreeperiod.exceptions;

public class CycleNotFoundException extends RuntimeException{
    public CycleNotFoundException(String message){
        super(message);
    }
}
