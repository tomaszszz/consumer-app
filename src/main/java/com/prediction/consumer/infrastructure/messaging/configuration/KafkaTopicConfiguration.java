package com.prediction.consumer.infrastructure.messaging.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfiguration {
    @Bean
    public NewTopic weatherTopic() {
        return new NewTopic("weather-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic financeTopic() {
        return new NewTopic("finance-topic", 1, (short) 1);
    }
}
