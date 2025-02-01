package com.prediction.consumer.healthchecks;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

@RequiredArgsConstructor
public class KafkaHealthIndicator extends AbstractHealthIndicator {
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        Status status = kafkaListenerEndpointRegistry.isRunning() ? Status.UP : Status.DOWN;
        builder.status(status);
    }
}
