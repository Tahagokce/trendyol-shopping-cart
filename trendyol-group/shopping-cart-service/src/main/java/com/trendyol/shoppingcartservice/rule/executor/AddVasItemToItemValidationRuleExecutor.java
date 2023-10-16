package com.trendyol.shoppingcartservice.rule.executor;

import com.trendyol.common.constant.ApplicationConstant;
import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.AddVasItemToItemRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddVasItemToItemValidationRuleExecutor {
    private final List<AddVasItemToItemRule> addVasItemToItemRules;

    public void validator(VasItemDocument vasItem, CartItemDocument cartItem){
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, "AddVasItemToItemValidationRuleExecutor", "addVasItemToItemRules", "Rule validator started.");
        Optional<ValidationMessageResource> validationMessage = addVasItemToItemRules.stream()
                .sorted(Comparator.comparingInt(AddVasItemToItemRule::order))
                .map(rule -> rule.isValid(vasItem, cartItem))
                .filter(vm -> !vm.isValid())
                .findFirst();

        if (validationMessage.isPresent()){
            throw new RuleNotValidException(validationMessage.get().getMessage());
        }
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, "AddVasItemToItemValidationRuleExecutor", "addVasItemToItemRules", "Successfully passed the rules");
    }
}
