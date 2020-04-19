package com.iti.password.validator.metrics.micrometer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import io.micrometer.core.instrument.Counter;

@ExtendWith(SpringExtension.class)
class MicrometerPasswordValidationCounterTest {

    @Mock(name = "validRequestCounter")
    private Counter validRequestCounter;
    @Mock(name = "invalidRequestCounter")
    private Counter invalidRequestCounter;

    @InjectMocks
    private MicrometerPasswordValidationCounter subject;

    @Test
    public void shouldCallValidCounter() {
        //given
        boolean valid = true;

        //when
        subject.compute(valid);

        //then
        verify(validRequestCounter).increment();
    }

    @Test
    public void shouldCallInvalidCounter() {
        //given
        boolean invalid = false;

        //when
        subject.compute(invalid);

        //then
        verify(invalidRequestCounter).increment();
    }
}