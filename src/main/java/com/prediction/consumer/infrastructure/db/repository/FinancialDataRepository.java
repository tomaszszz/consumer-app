package com.prediction.consumer.infrastructure.db.repository;

import com.prediction.consumer.infrastructure.db.entities.FinancialData;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialDataRepository extends ListCrudRepository<FinancialData, String> {
}
