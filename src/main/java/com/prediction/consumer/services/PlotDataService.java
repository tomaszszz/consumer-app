package com.prediction.consumer.services;

import com.prediction.consumer.dto.PlotDataConverter;
import com.prediction.consumer.dto.PlotDataDTO;
import com.prediction.consumer.infrastructure.db.repository.FinancialDataRepository;
import com.prediction.consumer.infrastructure.db.repository.PlotDataRepository;
import com.prediction.consumer.infrastructure.db.repository.WeatherDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlotDataService {
    private final PlotDataRepository plotDataRepository;
    private final WeatherDataRepository weatherDataRepository;
    private final FinancialDataRepository financialDataRepository;
    private final StockPricePredictorService stockPricePredictorService;

    public PlotDataService(PlotDataRepository plotDataRepository,
                           WeatherDataRepository weatherDataRepository, FinancialDataRepository financialDataRepository) throws Exception {
        this.plotDataRepository = plotDataRepository;
        this.weatherDataRepository = weatherDataRepository;
        this.financialDataRepository = financialDataRepository;
        this.stockPricePredictorService = new StockPricePredictorService();
    }

    @Transactional
    public PlotDataDTO saveAndGetPredictedPlotData(LocalDate date, double temperature) throws Exception {
        trainPlotDataModel();
        PlotDataDTO plotDataDTO = PlotDataDTO.builder()
                .timestamp(date)
                .temperature(temperature)
                .stockPrice(BigDecimal.valueOf(predictStockPrice(temperature)))
                .build();
        weatherDataRepository.save(PlotDataConverter.toWeatherData(plotDataDTO));
        financialDataRepository.save(PlotDataConverter.toFinancialData(plotDataDTO));
        return plotDataDTO;
    }

    public List<PlotDataDTO> fetchPlotData() {
        return plotDataRepository.fetchPlotData();
    }

    private void trainPlotDataModel() throws Exception {
        double[][] temperatures = fetchPlotData().stream()
                .map(PlotDataDTO::getTemperature)
                .map(temperature -> new double[]{temperature})
                .toArray(double[][]::new);

        double[][] stockPrices = fetchPlotData().stream()
                .map(PlotDataDTO::getStockPrice)
                .map(stockPrice -> new double[]{stockPrice.doubleValue()})
                .toArray(double[][]::new);
        stockPricePredictorService.train(temperatures, stockPrices);
    }

    private double predictStockPrice(double temperature) throws Exception {
        return stockPricePredictorService.predict(temperature);
    }
}
