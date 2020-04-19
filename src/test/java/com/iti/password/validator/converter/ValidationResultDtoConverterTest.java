package com.iti.password.validator.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.dto.ValidationResultDto;
import com.iti.password.validator.service.model.Password;

@ExtendWith(SpringExtension.class)
class ValidationResultDtoConverterTest {
    private static final String PASSWORD = "password";
    @InjectMocks
    private ValidationResultDtoConverter converter;

    @Test
    public void shouldConvertProperlyWhenIsValid() {
        //given
        boolean isValid = true;

        //when
        ValidationResultDto converted = converter.convert(isValid);

        //then
        assertThat(converted.isValid(), is(equalTo(isValid)));
    }

    @Test
    public void shouldConvertProperlyWhenIsInvalid() {
        //given
        boolean isInvalid = false;

        //when
        ValidationResultDto converted = converter.convert(isInvalid);

        //then
        assertThat(converted.isValid(), is(equalTo(isInvalid)));
    }
}