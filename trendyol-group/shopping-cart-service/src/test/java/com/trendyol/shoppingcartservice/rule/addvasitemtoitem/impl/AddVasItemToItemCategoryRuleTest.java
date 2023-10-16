package com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddVasItemToItemCategoryRuleTest {

    @InjectMocks
    private AddVasItemToItemCategoryRule addVasItemToItemCategoryRule;

    @Test
    void testIsValid_whenIfHasAddedVasItem() {
        // arrange
        VasItemDocument vasItem = new EasyRandom().nextObject(VasItemDocument.class);
        vasItem.setCategoryId(3442L);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);

        // act
        ValidationMessageResource actual = addVasItemToItemCategoryRule.isValid(vasItem, cartItem);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testIsValid_whenIfHasNotAddedVasItem() {
        // arrange
        VasItemDocument vasItem = new EasyRandom().nextObject(VasItemDocument.class);
        vasItem.setCategoryId(2112L);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);

        // act
        ValidationMessageResource actual = addVasItemToItemCategoryRule.isValid(vasItem, cartItem);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addVasItemToItemCategoryRule.order();

        // assert
        Assertions.assertEquals(1, actual);
    }
}
