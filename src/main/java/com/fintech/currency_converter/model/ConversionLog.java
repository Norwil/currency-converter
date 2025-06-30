package com.fintech.currency_converter.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
public class ConversionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromCurrency;
    private String toCurrency;
    private Double originalAmount;
    private Double convertedAmount;
    private Double exchangeRate;
    private LocalDateTime timestamp;

    // Getters and setters 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }

    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }

    public Double getOriginalAmount() { return originalAmount; }
    public void setOriginalAmount(Double originalAmount) { this.originalAmount = originalAmount; }

    public Double getConvertedAmount() { return convertedAmount; }
    public void setConvertedAmount(Double convertedAmount) { this.convertedAmount = convertedAmount; }

    public Double getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(Double exchangeRate) { this.exchangeRate = exchangeRate; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
}
