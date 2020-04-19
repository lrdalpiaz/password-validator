package com.iti.password.validator.service.validator.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.iti.password.validator.service.model.Password;
import com.iti.password.validator.service.validator.PasswordValidator;

@Component
public class RegexPasswordValidator implements PasswordValidator {
    private final Pattern p;

    public RegexPasswordValidator(@Value("${password.validator.regex.pattern}") String pattern) {
        p = Pattern.compile(pattern);
    }

    public boolean validate(Password password) {
        Matcher m = p.matcher(password.getPassword());

        return m.matches();
    }
}
