package com.fintech.currency_converter.model;

import java.util.Map;

public class ExchangeRateResponse {
    private Map<String, Double> data;

    public ExchangeRateResponse() {}

    public Map<String, Double> getData() {
        return data;
    }
    public void setData(Map<String, Double> data) {
        this.data = data;
    }
}
