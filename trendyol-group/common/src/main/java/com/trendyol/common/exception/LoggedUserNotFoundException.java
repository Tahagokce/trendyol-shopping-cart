package com.trendyol.common.exception;

public class LoggedUserNotFoundException extends RuntimeException{
    public LoggedUserNotFoundException() {
        super("User not found.");
    }
}
