package com.iti.password.validator.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.iti.password.validator.metrics.PasswordValidationCounter;
import com.iti.password.validator.service.model.Password;
import com.iti.password.validator.service.validator.PasswordValidator;

@ExtendWith(SpringExtension.class)
class PasswordValidatorServiceTest {
    public static final String PASSWORD = "password";
    @Mock
    private PasswordValidator validator;
    @Mock
    private PasswordValidationCounter validationCounter;
    @InjectMocks
    private PasswordValidatorService subject;

    @Test
    public void shouldCallCounterWithInvalid() {
        //given
        Password password = Password.builder()
                .password(PASSWORD)
                .build();
        when(validator.validate(any())).thenReturn(true);

        //when
        subject.validate(password);

        //then
        verify(validationCounter).compute(true);
    }

    @Test
    public void shouldCallCounterWithValid() {
        //given
        Password password = Password.builder()
                .password(PASSWORD)
                .build();
        when(validator.validate(any())).thenReturn(false);

        //when
        subject.validate(password);

        //then
        verify(validationCounter).compute(false);
    }
}