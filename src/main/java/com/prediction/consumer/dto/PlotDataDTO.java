package com.prediction.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
public class PlotDataDTO {
    private LocalDate timestamp;
    private Double temperature;
    private BigDecimal stockPrice;

    public PlotDataDTO(LocalDate timestamp, BigDecimal stockPrice, Double temperature) {
        this.timestamp = timestamp;
        this.stockPrice = stockPrice;
        this.temperature = temperature;
    }
}
