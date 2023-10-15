package com.trendyol.shoppingcartservice.rule.executor;

import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddItemValidationRuleExecutor {

    private final List<AddItemRule> addItemRules;

    public void validator(CartDocument cart){
        Optional<ValidationMessageResource> validationMessage = addItemRules.stream()
                .sorted(Comparator.comparingInt(AddItemRule::order))
                .map(rule -> rule.isValid(cart))
                .filter(vm -> !vm.isValid())
                .findFirst();

        if (validationMessage.isPresent()){
            throw new RuleNotValidException(validationMessage.get().getMessage());
        }
    }
}
