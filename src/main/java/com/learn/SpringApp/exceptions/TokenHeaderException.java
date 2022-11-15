package com.learn.SpringApp.exceptions;

public class TokenHeaderException extends  RuntimeException {
    public TokenHeaderException(String message){
        super(message);
    }
}
