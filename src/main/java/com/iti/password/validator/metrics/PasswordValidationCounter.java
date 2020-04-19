package com.iti.password.validator.metrics;

public interface PasswordValidationCounter {
    void compute(boolean isValid);
}
