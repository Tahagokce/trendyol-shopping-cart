package com.trendyol.shoppingcartservice.card.util;

import com.trendyol.core.model.dto.AppliedPromotionDto;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.promotion.util.PromotionUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartUtil {
    public void calculateAmount(CartDocument cartDocument){
        AppliedPromotionDto appliedPromotion = PromotionUtil.applyMostAdvantagePromotion(cartDocument);
        cartDocument.setTotalAmount(appliedPromotion.getTotalAmount());
        cartDocument.setAppliedPromotionId(appliedPromotion.getAppliedPromotionId());
        cartDocument.setTotalDiscount(appliedPromotion.getTotalDiscount());
    }
}
