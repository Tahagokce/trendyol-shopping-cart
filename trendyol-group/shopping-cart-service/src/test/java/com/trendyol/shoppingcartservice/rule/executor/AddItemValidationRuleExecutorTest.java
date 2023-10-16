package com.trendyol.shoppingcartservice.rule.executor;

import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import com.trendyol.shoppingcartservice.rule.additem.impl.AddItemMaximumAmountRule;
import com.trendyol.shoppingcartservice.rule.additem.impl.AddItemMaximumCountRule;
import com.trendyol.shoppingcartservice.rule.additem.impl.AddItemMaximumQuantityRule;
import com.trendyol.shoppingcartservice.rule.additem.impl.AddItemUniqueMaximumCountRule;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddItemValidationRuleExecutorTest {

    private AddItemValidationRuleExecutor addItemValidationRuleExecutor;

    @Mock
    private AddItemMaximumCountRule addItemMaximumCountRule;

    @Mock
    private AddItemMaximumAmountRule addItemMaximumAmountRule;

    @Mock
    private AddItemMaximumQuantityRule addItemMaximumQuantityRule;

    @Mock
    private AddItemUniqueMaximumCountRule addItemUniqueMaximumCountRule;

    @BeforeEach
    void setUp() {
        List<AddItemRule> addItemRules = List.of(
                addItemMaximumCountRule,
                addItemMaximumAmountRule,
                addItemMaximumQuantityRule,
                addItemUniqueMaximumCountRule
        );

        addItemValidationRuleExecutor = new AddItemValidationRuleExecutor(addItemRules);
    }

    @Test
    void testValidator_whenIsValid() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);
        ValidationMessageResource validationMessage = ValidationMessageResource.buildSuccess();

        Mockito.when(addItemMaximumCountRule.isValid(any())).thenReturn(validationMessage);
        Mockito.when(addItemMaximumAmountRule.isValid(any())).thenReturn(validationMessage);
        Mockito.when(addItemMaximumQuantityRule.isValid(any())).thenReturn(validationMessage);
        Mockito.when(addItemUniqueMaximumCountRule.isValid(any())).thenReturn(validationMessage);

        // act
        addItemValidationRuleExecutor.validator(cart);

        // assert
        Mockito.verify(addItemMaximumCountRule).isValid(any());
        Mockito.verify(addItemMaximumAmountRule).isValid(any());
        Mockito.verify(addItemMaximumQuantityRule).isValid(any());
        Mockito.verify(addItemUniqueMaximumCountRule).isValid(any());
    }

    @Test
    void testValidator_whenIsNotValid() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);
        ValidationMessageResource validationMessage = ValidationMessageResource.buildError("error");

        Mockito.when(addItemMaximumCountRule.isValid(any())).thenReturn(validationMessage);

        // act
        Executable actual = () -> addItemValidationRuleExecutor.validator(cart);

        // assert
        Assertions.assertThrows(RuleNotValidException.class, actual);
        Mockito.verify(addItemMaximumCountRule).isValid(any());
    }
}
