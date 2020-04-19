package com.iti.password.validator.converter;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.iti.password.validator.dto.PasswordDto;
import com.iti.password.validator.service.model.Password;

@ExtendWith(SpringExtension.class)
class PasswordDtoToModelConverterTest {

    public static final String PASSWORD = "password";
    @InjectMocks
    private PasswordDtoToModelConverter converter;

    @Test
    public void shouldConvertProperly() {
        //given
        PasswordDto password = PasswordDto.builder()
                .password(PASSWORD)
                .build();

        //when
        Password converted = converter.convert(password);

        //then
        assertThat(converted.getPassword(), is(equalTo(PASSWORD)));
    }
}