package com.trendyol.core.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class SecurityUtil {
    public Optional<Long> getLoggedUserId() {
        // Here loggedUserId comes from SecurityContext
        return Optional.of(1L);
    }

    public String getLoggedTransactionId() {
        // Here loggedTransactionId comes from SecurityContext
        return UUID.randomUUID().toString();
    }
}
