package com.iti.password.validator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iti.password.validator.metrics.PasswordValidationCounter;
import com.iti.password.validator.service.model.Password;
import com.iti.password.validator.service.validator.PasswordValidator;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PasswordValidatorService {
    @Autowired
    private PasswordValidator validator;
    @Autowired
    private PasswordValidationCounter validationCounter;

    public boolean validate(Password password) {
        boolean isValid = validator.validate(password);

        validationCounter.compute(isValid);
        log.info("Password is Valid? {}", isValid);
        return isValid;
    }
}