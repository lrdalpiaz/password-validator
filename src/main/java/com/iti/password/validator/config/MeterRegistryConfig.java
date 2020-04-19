package com.iti.password.validator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MeterRegistryConfig {

    @Bean(name = "validRequestCounter")
    public Counter validRequestCounter(MeterRegistry meterRegistry) {
        return Counter
                .builder("requests")
                .description("Valid Requests counter")
                .tags("password", "valid")
                .register(meterRegistry);
    }

    @Bean(name = "invalidRequestCounter")
    public Counter invalidRequestCounter(MeterRegistry meterRegistry) {
        return Counter
                .builder("requests")
                .description("Valid Requests counter")
                .tags("password", "invalid")
                .register(meterRegistry);
    }
}
