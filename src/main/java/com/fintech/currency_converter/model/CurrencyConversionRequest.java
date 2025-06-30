package com.fintech.currency_converter.model;

public class CurrencyConversionRequest {

    private String fromCurrency;
    private String toCurrency;
    private Double amount;

    // Constructor
    public CurrencyConversionRequest() {}

    public CurrencyConversionRequest(String fromCurrency, String toCurrency, Double amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
