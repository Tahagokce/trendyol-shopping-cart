package com.trendyol.common.advice;

import com.trendyol.common.exception.LoggedUserNotFoundException;
import com.trendyol.common.exception.RuleNotValidException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({LoggedUserNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleLoggedUserNotFoundExceptions(Exception e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e){
        String validationMessage = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(validationMessage);
    }

    @ExceptionHandler({RuleNotValidException.class})
    public ResponseEntity<String> handleRuleException(RuleNotValidException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
