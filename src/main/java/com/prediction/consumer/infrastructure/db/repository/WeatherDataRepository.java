package com.prediction.consumer.infrastructure.db.repository;

import com.prediction.consumer.infrastructure.db.entities.WeatherData;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherDataRepository extends ListCrudRepository<WeatherData, String> {
}
