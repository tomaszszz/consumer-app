package com.prediction.consumer.infrastructure.http;

import com.prediction.consumer.PlotDataService;
import com.prediction.consumer.dto.PlotDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data/plot")
@AllArgsConstructor
public class DataRestController {
    private PlotDataService plotDataService;

    @GetMapping
    public ResponseEntity<List<PlotDTO>> getPlotData() {
        return ResponseEntity.ok(plotDataService.fetchPlotData());
    }
}
