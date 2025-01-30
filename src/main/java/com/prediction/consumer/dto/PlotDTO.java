package com.prediction.consumer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class PlotDTO {
    private LocalDate timestamp;
    private Double temperature;
    private BigDecimal stockPrice;

    public PlotDTO(LocalDate timestamp, BigDecimal stockPrice, Double temperature) {
        this.timestamp = timestamp;
        this.stockPrice = stockPrice;
        this.temperature = temperature;
    }
}
