package com.iti.password.validator.metrics.micrometer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.iti.password.validator.metrics.PasswordValidationCounter;
import io.micrometer.core.instrument.Counter;

@Component
public class MicrometerPasswordValidationCounter implements PasswordValidationCounter {
    @Qualifier("validRequestCounter")
    @Autowired
    private Counter invalidRequestCounter;
    @Qualifier("invalidRequestCounter")
    @Autowired
    private Counter validRequestCounter;

    public void compute(boolean isValid) {
        if (isValid) {
            incrementValid();
        } else {
            incrementInvalid();
        }
    }
    private void incrementValid() {
        validRequestCounter.increment();
    }

    private void incrementInvalid() {
        invalidRequestCounter.increment();
    }
}
