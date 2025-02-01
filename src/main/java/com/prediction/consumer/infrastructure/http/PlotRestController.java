package com.prediction.consumer.infrastructure.http;

import com.prediction.consumer.services.PlotDataService;
import com.prediction.consumer.dto.PlotDataDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plot")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // CORS for local development, frontend needs to be running on default port
public class PlotRestController {
    private PlotDataService plotDataService;

    @GetMapping("/data")
    public ResponseEntity<List<PlotDataDTO>> getPlotData() {
        return ResponseEntity.ok(plotDataService.fetchPlotData());
    }

    @PostMapping("/predict")
    public ResponseEntity<PlotDataDTO> predictStockPriceForTemperatureAndDate
            (@RequestBody PredictStockRequest predictStockRequest) throws Exception {
        return ResponseEntity.ok(plotDataService
                .saveAndGetPredictedPlotData(predictStockRequest.getDate(), predictStockRequest.getTemperature()));
    }
}
