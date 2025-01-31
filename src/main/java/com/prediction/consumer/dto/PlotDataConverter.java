package com.prediction.consumer.dto;

import com.prediction.consumer.infrastructure.db.entities.FinancialData;
import com.prediction.consumer.infrastructure.db.entities.WeatherData;

public class PlotDataConverter {
    public static WeatherData toWeatherData(PlotDataDTO weatherData) {
        return WeatherData.builder()
                .timestamp(weatherData.getTimestamp())
                .temperature(weatherData.getTemperature())
                .build();
    }

    public static FinancialData toFinancialData(PlotDataDTO financialData) {
        return FinancialData.builder()
                .timestamp(financialData.getTimestamp())
                .stockPrice(financialData.getStockPrice())
                .build();
    }
}
