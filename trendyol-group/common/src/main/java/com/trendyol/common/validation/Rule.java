package com.trendyol.common.validation;

public interface Rule<T> {
    boolean isValid(T object);
    int order();
}
