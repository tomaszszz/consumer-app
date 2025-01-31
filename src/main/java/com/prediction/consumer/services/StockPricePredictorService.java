package com.prediction.consumer.services;

import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import java.util.ArrayList;

public class StockPricePredictorService {
    private final LinearRegression model;
    private final Instances dataset;

    public StockPricePredictorService() throws Exception {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("temperature"));
        attributes.add(new Attribute("stock_price"));

        dataset = new Instances("StockPriceDataset", attributes, 0);
        dataset.setClassIndex(1); // attribute 1 is stock_price - it should be predicted
        model = new LinearRegression();
    }

    public void train(double[][] temperatureData, double[][] stockPriceData) throws Exception {
        dataset.clear();
        for (int i = 0; i < temperatureData.length; i++) {
            double[] values = new double[]{temperatureData[i][0], stockPriceData[i][0]};
            dataset.add(new DenseInstance(1.0, values));
        }
        model.buildClassifier(dataset);
    }

    public double predict(double temperature) throws Exception {
        double[] values = new double[]{temperature, Double.NaN};
        DenseInstance instance = new DenseInstance(1.0, values);
        instance.setDataset(dataset);
        return model.classifyInstance(instance);
    }
}