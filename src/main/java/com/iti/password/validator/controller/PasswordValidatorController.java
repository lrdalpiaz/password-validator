package com.iti.password.validator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iti.password.validator.converter.PasswordDtoToModelConverter;
import com.iti.password.validator.converter.ValidationResultDtoConverter;
import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.dto.ValidationResultDto;
import com.iti.password.validator.metrics.PasswordValidationCounter;
import com.iti.password.validator.service.PasswordValidatorService;
import com.iti.password.validator.service.validator.PasswordValidator;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "password", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Timed("PasswordCheckerController")
public class PasswordValidatorController {

    @Autowired
    private ValidationResultDtoConverter converter;
    @Autowired
    private PasswordDtoToModelConverter dtoToModelConverter;
    @Autowired
    private PasswordValidatorService passwordValidatorService;


    @ApiOperation(
            value = "Verifica se um password é válido",
            response = ValidationResultDto.class,
            nickname = "check-password")
    @PostMapping(value = "/validation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ValidationResultDto> validate(@RequestBody PasswordDto passwordDto) {
        return Mono.fromCallable(
                () -> execute(passwordDto))
                .metrics()
                .log()
                .onErrorReturn(ValidationResultDto.builder().isValid(false).build());
    }

    private ValidationResultDto execute(PasswordDto passwordDto) {
        boolean isValid = passwordValidatorService.validate(dtoToModelConverter.convert(passwordDto));
        return converter.convert(isValid);
    }
}
