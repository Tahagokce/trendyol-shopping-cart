package com.trendyol.shoppingcartservice.promotion.util;

import com.trendyol.core.model.dto.AppliedPromotionDto;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@UtilityClass
public class PromotionUtil {

    public AppliedPromotionDto applyMostAdvantagePromotion(CartDocument cart) {
        return Stream.of(
                        getSameSellerPromotion(cart),
                        getCategoryPromotion(cart),
                        getTotalPricePromotion(cart)
                ).min(Comparator.comparingDouble(AppliedPromotionDto::getTotalAmount))
                .orElse(AppliedPromotionDto.of(0L, calculatePrice(cart), 0.0));

    }

    public AppliedPromotionDto getSameSellerPromotion(CartDocument cart) {
        double totalPrice = calculatePrice(cart);
        Long promotionId = null;
        double totalAmount = totalPrice;
        double totalDiscount = 0.0;
        boolean isSame;

        if (cart.getItems().size() > 1) {
            isSame = cart.getItems().stream()
                    .map(CartItemDocument::getSellerId)
                    .distinct()
                    .count() == 1;

        } else {
            isSame = !CollectionUtils.isEmpty(cart.getItems()) && cart.getItems().get(0).getQuantity() > 1;
        }

        if (isSame) {
            promotionId = 9909L;
            totalDiscount = totalPrice * 10.0 / 100;
            totalAmount = totalPrice - totalDiscount;
        }

        return AppliedPromotionDto.of(promotionId, totalAmount, totalDiscount);
    }

    public AppliedPromotionDto getCategoryPromotion(CartDocument cart) {
        double totalPrice = calculatePrice(cart);
        Long promotionId = null;
        double totalAmount = totalPrice;
        double totalDiscount = 0.0;

        List<CartItemDocument> cartItemsWithPromotion = cart.getItems().stream()
                .filter(ctd -> Objects.equals(ctd.getCategoryId(), 3003L))
                .toList();

        if (!CollectionUtils.isEmpty(cartItemsWithPromotion)) {
            promotionId = 5676L;
            totalDiscount = cartItemsWithPromotion.stream()
                    .mapToDouble(ci -> ci.getPrice() * 5.0 / 100)
                    .sum();

            totalAmount = totalPrice - totalDiscount;
        }

        return AppliedPromotionDto.of(promotionId, totalAmount, totalDiscount);
    }

    public AppliedPromotionDto getTotalPricePromotion(CartDocument cart) {
        double totalPrice = calculatePrice(cart);
        Long promotionId = null;
        double totalAmount;
        double totalDiscount = 0.0;

        if (totalPrice >= 500) {
            promotionId = 1232L;
            if (totalPrice < 5000) {
                totalDiscount = 250;
            } else if (totalPrice < 10000) {
                totalDiscount = 500;
            } else if (totalPrice < 50000) {
                totalDiscount = 1000;
            } else {
                totalDiscount = 2000;
            }
        }

        totalAmount = totalPrice - totalDiscount;

        return AppliedPromotionDto.of(promotionId, totalAmount, totalDiscount);
    }


    private Double calculatePrice(CartDocument cart) {
        return cart.getItems().stream()
                .mapToDouble(PromotionUtil::calculatePrice)
                .sum();
    }

    private Double calculatePrice(CartItemDocument cartItem) {
        return (cartItem.getPrice() * cartItem.getQuantity()) + cartItem.getVasItems().stream().mapToDouble(vasItem -> vasItem.getPrice() * vasItem.getQuantity()).sum();
    }
}
