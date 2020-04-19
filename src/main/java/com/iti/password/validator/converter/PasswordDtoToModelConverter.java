package com.iti.password.validator.converter;

import org.springframework.stereotype.Component;

import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.service.model.Password;

@Component
public class PasswordDtoToModelConverter {

    public Password convert(PasswordDto passwordDto) {
        return Password.builder()
                .password(passwordDto.getPassword())
                .build();
    }

}
