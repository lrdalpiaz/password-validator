package com.iti.password.validator.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.metrics.PasswordValidationCounter;
import com.iti.password.validator.service.model.Password;
import com.iti.password.validator.service.validator.PasswordValidator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PasswordValidatorServiceIT {
    private static final String VALID_PASSWORD = "sak#$75AC";
    private static final String PASSWD_WITHOUT_NUMBER = "sak#$wecAC";
    private static final String PASSWD_WITHOUT_ESPCIALS = "sakfew75AC";
    private static final String PASSWD_WITHOUT_LOWER_CASE = "ASGF#$75AC";
    private static final String PASSWD_WITHOUT_UPPER = "sak#$75df";
    private static final String PASSWD_TOO_SHORT = "k#$75df";

    @Autowired
    private PasswordValidatorService subject;

    @Test
    public void shouldValidate() {
        //given
        Password password = Password.builder()
                .password(VALID_PASSWORD)
                .build();
        //when
        boolean result = subject.validate(password);

        //then
        assertThat(result, is(equalTo(true)));
    }
    @Test
    public void shouldReturnInvalidWithoutNumbers() {
        //given
        Password password = Password.builder()
                .password(PASSWD_WITHOUT_NUMBER)
                .build();
        //when
        boolean result = subject.validate(password);

        //then
        assertThat(result, is(equalTo(false)));
    }

    @Test
    public void shouldReturnInvalidWithoutEspecialChars() {
        //given
        Password password = Password.builder()
                .password(PASSWD_WITHOUT_ESPCIALS)
                .build();
        //when
        boolean result = subject.validate(password);

        //then
        assertThat(result, is(equalTo(false)));
    }

    @Test
    public void shouldReturnInvalidWithoutLowerCase() {
        //given
        Password password = Password.builder()
                .password(PASSWD_WITHOUT_LOWER_CASE)
                .build();
        //when
        boolean result = subject.validate(password);

        //then
        assertThat(result, is(equalTo(false)));
    }

    @Test
    public void shouldReturnInvalidWithoutUpperCase() {
        //given
        Password password = Password.builder()
                .password(PASSWD_WITHOUT_UPPER)
                .build();
        //when
        boolean result = subject.validate(password);

        //then
        assertThat(result, is(equalTo(false)));
    }

    @Test
    public void shouldReturnInvalidWhenTooShort() {
        //given
        Password password = Password.builder()
                .password(PASSWD_TOO_SHORT)
                .build();
        //when
        boolean result = subject.validate(password);

        //then
        assertThat(result, is(equalTo(false)));
    }
}