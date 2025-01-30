package com.prediction.consumer.infrastructure.db.repository;

import com.prediction.consumer.infrastructure.db.entities.PlotData;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlotDataRepository extends ListCrudRepository<PlotData, String> {
    List<PlotData> findAllByTimestamp(LocalDate timestamp);
}
