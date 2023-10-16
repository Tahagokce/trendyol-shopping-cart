package com.trendyol.shoppingcartservice.rule.executor;

import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.AddVasItemToItemRule;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl.AddVasItemToItemCategoryRule;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl.AddVasItemToItemMaxQuantityRule;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl.AddVasItemToItemPriceRule;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl.AddVasItemToItemSellerRule;
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
class AddVasItemToItemValidationRuleExecutorTest {

    private AddVasItemToItemValidationRuleExecutor addVasItemToItemValidationRuleExecutor;

    @Mock
    private AddVasItemToItemCategoryRule addVasItemToItemCategoryRule;

    @Mock
    private AddVasItemToItemMaxQuantityRule addVasItemToItemMaxQuantityRule;

    @Mock
    private AddVasItemToItemPriceRule addVasItemToItemPriceRule;

    @Mock
    private AddVasItemToItemSellerRule addVasItemToItemSellerRule;

    @BeforeEach
    void setUp() {
        List<AddVasItemToItemRule> addVasItemToItemRules = List.of(
                addVasItemToItemCategoryRule,
                addVasItemToItemMaxQuantityRule,
                addVasItemToItemPriceRule,
                addVasItemToItemSellerRule
        );

        addVasItemToItemValidationRuleExecutor = new AddVasItemToItemValidationRuleExecutor(addVasItemToItemRules);
    }

    @Test
    void testValidator_whenIsValid() {
        // arrange
        VasItemDocument vasItem = new EasyRandom().nextObject(VasItemDocument.class);
        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        ValidationMessageResource validationMessage = ValidationMessageResource.buildSuccess();

        Mockito.when(addVasItemToItemCategoryRule.isValid(any(), any())).thenReturn(validationMessage);
        Mockito.when(addVasItemToItemMaxQuantityRule.isValid(any(), any())).thenReturn(validationMessage);
        Mockito.when(addVasItemToItemPriceRule.isValid(any(), any())).thenReturn(validationMessage);
        Mockito.when(addVasItemToItemSellerRule.isValid(any(), any())).thenReturn(validationMessage);

        // act
        addVasItemToItemValidationRuleExecutor.validator(vasItem, cartItem);

        // assert
        Mockito.verify(addVasItemToItemCategoryRule).isValid(any(), any());
        Mockito.verify(addVasItemToItemMaxQuantityRule).isValid(any(), any());
        Mockito.verify(addVasItemToItemPriceRule).isValid(any(), any());
        Mockito.verify(addVasItemToItemSellerRule).isValid(any(), any());
    }

    @Test
    void testValidator_whenIsNotValid() {
        // arrange
        VasItemDocument vasItem = new EasyRandom().nextObject(VasItemDocument.class);
        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        ValidationMessageResource validationMessage = ValidationMessageResource.buildError("error");
        Mockito.when(addVasItemToItemCategoryRule.isValid(any(), any())).thenReturn(validationMessage);

        // act
        Executable actual = () -> addVasItemToItemValidationRuleExecutor.validator(vasItem, cartItem);

        // assert
        Assertions.assertThrows(RuleNotValidException.class, actual);
        Mockito.verify(addVasItemToItemCategoryRule).isValid(any(), any());

    }
}
