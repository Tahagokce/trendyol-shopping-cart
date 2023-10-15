package com.trendyol.common.exception;

public class RuleNotValidException extends RuntimeException{
    public RuleNotValidException() {
        super("Rule not valid");
    }

    public RuleNotValidException(String message) {
        super(message);
    }
}
