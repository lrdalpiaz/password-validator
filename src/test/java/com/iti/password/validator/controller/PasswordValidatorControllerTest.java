package com.iti.password.validator.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.iti.password.validator.converter.PasswordDtoToModelConverter;
import com.iti.password.validator.converter.ValidationResultDtoConverter;
import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.dto.ValidationResultDto;
import com.iti.password.validator.service.PasswordValidatorService;
import com.iti.password.validator.service.model.Password;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PasswordValidatorController.class)
class PasswordValidatorControllerTest {
    private static final String VALID_PASSWORD = "sak#$75AC";
    private static final String INVALID = "sak#$wecAC";

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private PasswordValidatorService passwordValidatorService;
    @MockBean
    private ValidationResultDtoConverter converter;
    @MockBean
    private PasswordDtoToModelConverter dtoToModelConverter;

    @Test
    public void shouldAcceptPassword() {
        //given
        when(passwordValidatorService.validate(any())).thenReturn(true);
        when(converter.convert(true)).thenReturn(ValidationResultDto.builder().isValid(true).build());
        when(dtoToModelConverter.convert(any())).thenReturn(Password.builder().build());

        //when
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(VALID_PASSWORD).build()))
                .exchange()
        //then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(true);

        verify(passwordValidatorService).validate(any());
        verify(converter).convert(true);
        verify(dtoToModelConverter).convert(any());
    }

    @Test
    public void shouldReturnInvalid() {
        //given
        when(passwordValidatorService.validate(any())).thenReturn(false);
        when(converter.convert(false)).thenReturn(ValidationResultDto.builder().isValid(false).build());
        when(dtoToModelConverter.convert(any())).thenReturn(Password.builder().build());

        //when
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(INVALID).build()))
                .exchange()
        //then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);

        verify(passwordValidatorService).validate(any());
        verify(converter).convert(false);
        verify(dtoToModelConverter).convert(any());
    }
}