package com.iti.password.validator.converter;

import org.springframework.stereotype.Component;

import com.iti.password.validator.dto.ValidationResultDto;

@Component
public class ValidationResultDtoConverter {

    public ValidationResultDto convert(boolean isValid) {
        return ValidationResultDto.builder()
                .isValid(isValid)
                .build();
    }
}
