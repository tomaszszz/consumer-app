package com.prediction.consumer.infrastructure.db.repository;

import com.prediction.consumer.dto.PlotDTO;
import com.prediction.consumer.infrastructure.db.entities.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlotDataRepository extends JpaRepository<FinancialData, Long> {

    @Query("SELECT new com.prediction.consumer.dto.PlotDTO(f.timestamp, f.stockPrice, w.temperature) " +
            "FROM FinancialData f JOIN WeatherData w ON f.timestamp = w.timestamp")
    List<PlotDTO> fetchMergedData();
}