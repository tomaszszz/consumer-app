package com.prediction.consumer.infrastructure.db.repository;

import com.prediction.consumer.dto.PlotDataDTO;
import com.prediction.consumer.infrastructure.db.entities.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlotDataRepository extends JpaRepository<FinancialData, Long> {

    @Query("SELECT new com.prediction.consumer.dto.PlotDataDTO(f.timestamp, f.stockPrice, w.temperature) " +
            "FROM FinancialData f JOIN WeatherData w ON f.timestamp = w.timestamp")
    List<PlotDataDTO> fetchPlotData();

}