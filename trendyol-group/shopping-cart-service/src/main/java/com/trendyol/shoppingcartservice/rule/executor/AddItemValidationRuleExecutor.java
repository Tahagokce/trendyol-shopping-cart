package com.trendyol.shoppingcartservice.rule.executor;

import com.trendyol.common.constant.ApplicationConstant;
import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddItemValidationRuleExecutor {

    private final List<AddItemRule> addItemRules;

    public void validator(CartDocument cart){
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, "AddItemValidationRuleExecutor", "validator", "Rule validator started.");
        Optional<ValidationMessageResource> validationMessage = addItemRules.stream()
                .sorted(Comparator.comparingInt(AddItemRule::order))
                .map(rule -> rule.isValid(cart))
                .filter(vm -> !vm.isValid())
                .findFirst();

        if (validationMessage.isPresent()){
            throw new RuleNotValidException(validationMessage.get().getMessage());
        }
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, "AddItemValidationRuleExecutor", "validator", "Successfully passed the rules");
    }
}
