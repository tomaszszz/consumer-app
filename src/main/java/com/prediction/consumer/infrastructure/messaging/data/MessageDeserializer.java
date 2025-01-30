package com.prediction.consumer.infrastructure.messaging.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prediction.consumer.infrastructure.db.entities.FinancialData;
import com.prediction.consumer.infrastructure.db.entities.WeatherData;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageDeserializer {
    public static List<WeatherData> deserializeAndConvertWeatherData(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherData.class);
            return objectMapper.readValue(message, javaType);
        } catch (JsonProcessingException e) {
            System.out.println("Error when converting incoming message: " + e.getMessage());
        }
        return null;
    }

    public static List<FinancialData> deserializeAndConvertFinancialData(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, FinancialData.class);
            return objectMapper.readValue(message, javaType);
        } catch (JsonProcessingException e) {
            System.out.println("Error when converting incoming message: " + e.getMessage());
        }
        return null;
    }
}
