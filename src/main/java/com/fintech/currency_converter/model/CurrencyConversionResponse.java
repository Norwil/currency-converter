package com.fintech.currency_converter.model;

import java.time.LocalDateTime;

public class CurrencyConversionResponse {
    private String fromCurrency;
    private String toCurrency;
    private Double originalAmount;
    private Double convertedAmount;
    private Double exchangeRate;
    // private LocalDateTime timestamp;

    public CurrencyConversionResponse() {}

    public CurrencyConversionResponse(String fromCurrency, String toCurrency, Double originalAmount,
                                      Double convertedAmount, Double exchangeRate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;
        // this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getFromCurrency() {
        return fromCurrency;
    }
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }
    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }
    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Double getConvertedAmount() {
        return convertedAmount;
    }
    public void setConvertedAmount(Double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    // public LocalDateTime getTimestamp() {
    //     return timestamp;
    // }
    // public void setTimestamp(LocalDateTime timestamp) {
    //     this.timestamp = timestamp;
    // }
}
