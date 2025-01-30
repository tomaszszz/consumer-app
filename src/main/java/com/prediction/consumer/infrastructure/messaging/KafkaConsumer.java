package com.prediction.consumer.infrastructure.messaging;

import com.prediction.consumer.infrastructure.db.repository.FinancialDataRepository;
import com.prediction.consumer.infrastructure.db.repository.WeatherDataRepository;
import com.prediction.consumer.infrastructure.messaging.data.MessageDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final FinancialDataRepository financialDataRepository;
    private final WeatherDataRepository weatherDataRepository;

    @KafkaListener(topics = "finance-topic", groupId = "group-1")
    public void listenFinancialData(String message) {
        var convertedMessage = MessageDeserializer.deserializeAndConvertFinancialData(message);

        if (convertedMessage != null) {
            System.out.println("Received financial message, now processing: " + convertedMessage);
            financialDataRepository.saveAll(convertedMessage);
        } else {
            throw new MessageDeliveryException("Received empty message");
        }
    }

    @KafkaListener(topics = "weather-topic", groupId = "group-1")
    public void listenWeatherData(String message) {
        var convertedMessage = MessageDeserializer.deserializeAndConvertWeatherData(message);

        if (convertedMessage != null) {
            System.out.println("Received weather message, now processing: " + convertedMessage);
            weatherDataRepository.saveAll(convertedMessage);
        } else {
            throw new MessageDeliveryException("Received empty message");
        }
    }
}