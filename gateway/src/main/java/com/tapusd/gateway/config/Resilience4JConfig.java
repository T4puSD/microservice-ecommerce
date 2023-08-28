package com.tapusd.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(
                        CircuitBreakerConfig
                                .custom()
                                .maxWaitDurationInHalfOpenState(Duration.ofSeconds(10))
                                .permittedNumberOfCallsInHalfOpenState(3)
                                .waitDurationInOpenState(Duration.ofSeconds(60))
                                .build()
                )
                .timeLimiterConfig(
                        TimeLimiterConfig.custom()
                                // todos endpoint will alwasy fail because of 200ms config
                                .timeoutDuration(Duration.ofMillis(200))
                                .build()
                )
                .build());
    }
}
