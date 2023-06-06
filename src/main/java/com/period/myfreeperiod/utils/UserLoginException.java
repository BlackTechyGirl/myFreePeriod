package com.period.myfreeperiod.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;



public class UserLoginException extends RuntimeException{
    public UserLoginException(String message){
        super(message);
    }

}
