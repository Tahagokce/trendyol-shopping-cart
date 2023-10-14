package com.trendyol.shoppingcartservice.card.rule.factory;

import com.trendyol.common.validation.Rule;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.card.rule.CartRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartRuleExecutorFactory {

    private final List<CartRule> cartRules;

    public boolean isValid(CartDocument cartDocument){
       return cartRules.stream()
                .sorted(Comparator.comparingInt(Rule::order))
                .map(rule -> rule.isValid(cartDocument))
                .filter(isValid -> !isValid)
                .findFirst()
                .orElse(true);
    }
}
