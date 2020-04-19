package com.iti.password.validator.service.validator;

import com.iti.password.validator.service.model.Password;

public interface PasswordValidator {
    boolean validate(Password password);
}
