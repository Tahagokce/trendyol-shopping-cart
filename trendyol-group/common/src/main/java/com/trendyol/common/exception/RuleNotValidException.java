package com.trendyol.common.exception;

public class RuleNotValidException extends RuntimeException{
    public RuleNotValidException() {
        super("Rule not valid");
    }
}
