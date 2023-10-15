package com.trendyol.common.advice;

import com.trendyol.common.exception.RuleNotValidException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testHandleLoggedUserNotFoundExceptions() {
        // arrange
        String message = new EasyRandom().nextObject(String.class);
        Exception exception = new Exception(message);

        // act
        ResponseEntity<String> actual = globalExceptionHandler.handleLoggedUserNotFoundExceptions(exception);

        // assert
        assertEquals(actual.getBody(), message);
    }

    @Test
    void testHandleRuleException() {
        // arrange
        String message = "Rule not valid";
        RuleNotValidException exception = new RuleNotValidException();

        // act
        ResponseEntity<String> actual = globalExceptionHandler.handleRuleException(exception);

        // assert
        assertEquals(actual.getBody(), message);
    }
}
