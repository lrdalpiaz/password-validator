package com.iti.password.validator.error.handler;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.iti.password.validator.dto.ValidationResultDto;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class ErrorHandler extends DefaultErrorAttributes {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ValidationResultDto> handle(Exception e) {
        log.error(e.getMessage());
        return Mono.error(e);
    }
}
