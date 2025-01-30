package com.prediction.consumer;

import com.prediction.consumer.dto.PlotDTO;
import com.prediction.consumer.infrastructure.db.repository.PlotDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlotDataService {
    private PlotDataRepository plotDataRepository;

    public List<PlotDTO> fetchPlotData() {
        return plotDataRepository.fetchMergedData();
    }
}
