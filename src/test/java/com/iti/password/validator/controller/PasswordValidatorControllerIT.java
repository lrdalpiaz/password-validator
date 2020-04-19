package com.iti.password.validator.controller;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.dto.ValidationResultDto;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PasswordValidatorControllerIT {
    private static final String VALID_PASSWORD = "sak#$75AC";
    private static final String PASSWD_WITHOUT_NUMBER = "sak#$wecAC";
    private static final String PASSWD_WITHOUT_ESPCIALS = "sakfew75AC";
    private static final String PASSWD_WITHOUT_LOWER_CASE = "ASGF#$75AC";
    private static final String PASSWD_WITHOUT_UPPER = "sak#$75df";
    private static final String PASSWD_TOO_SHORT = "k#$75df";

    @Autowired
    private WebTestClient testClient;

    @Test
    public void shouldReturnBadRequest() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("cwecwe")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void shouldReturnInvalidWhenJsonIsEmpty() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);
    }

    @Test
    public void shouldAcceptPassword() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(VALID_PASSWORD).build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(true);
    }

    @Test
    public void shouldReturnInvalidWithoutNumbers() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(PASSWD_WITHOUT_NUMBER).build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);
    }

    @Test
    public void shouldReturnInvalidWithoutEspecialChars() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(PASSWD_WITHOUT_ESPCIALS).build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);
    }

    @Test
    public void shouldReturnInvalidWithoutLowerCase() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(PASSWD_WITHOUT_LOWER_CASE).build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);
    }

    @Test
    public void shouldReturnInvalidWithoutUpperCase() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(PASSWD_WITHOUT_UPPER).build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);
    }

    @Test
    public void shouldReturnInvalidWhenTooShort() {
        testClient.post()
                .uri("/api/v1/password/validation")
                .body(fromValue(PasswordDto.builder().password(PASSWD_TOO_SHORT).build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.valid").isBoolean()
                .jsonPath("$.valid").isEqualTo(false);
    }
}