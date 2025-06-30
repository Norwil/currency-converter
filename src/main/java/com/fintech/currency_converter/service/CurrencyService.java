package com.fintech.currency_converter.service;

import com.fintech.currency_converter.exception.CurrencyConversionException;
import com.fintech.currency_converter.model.CurrencyConversionRequest;
import com.fintech.currency_converter.model.CurrencyConversionResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CurrencyService {

    private final FreeCurrencyApiService freeCurrencyApiService;

    public CurrencyService(FreeCurrencyApiService freeCurrencyApiService) {
        this.freeCurrencyApiService = freeCurrencyApiService;
    }

    public CurrencyConversionResponse convertCurrency(CurrencyConversionRequest request) {
        // Validate input
        validateConversionRequest(request);

        // Get exchange rates
        Map<String, Double> exchangeRates = freeCurrencyApiService.getExchangeRates();

        // Calculate conversion
        double exchangeRate = calculateExchangeRate(
                request.getFromCurrency(),
                request.getToCurrency(),
                exchangeRates
        );

        double convertedAmount = request.getAmount() * exchangeRate;

        return new CurrencyConversionResponse(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getAmount(),
                convertedAmount,
                exchangeRate
        );

    }

    private void validateConversionRequest(CurrencyConversionRequest request) {
        if (request.getFromCurrency() == null || request.getFromCurrency().trim().isEmpty()) {
            throw new CurrencyConversionException("From currency is required");
        }

        if (request.getToCurrency() == null || request.getToCurrency().trim().isEmpty()) {
            throw new CurrencyConversionException("To currency is required");
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new CurrencyConversionException("Amount must be greater than 0");
        }

        if (!freeCurrencyApiService.isSupportedCurrency(request.getFromCurrency())) {
            throw new CurrencyConversionException("Unsupported from currency: " + request.getFromCurrency());
        }

        if (!freeCurrencyApiService.isSupportedCurrency(request.getToCurrency())) {
            throw new CurrencyConversionException("Unsupported to currency: " + request.getToCurrency());
        }
    }

    private double calculateExchangeRate(String fromCurrency, String toCurrency,
                                         Map<String, Double> rates) {
        // FreeCurrencyAPI returns rates relative to USD
        // So we need to convert: Amount * (1/fromRate) * toRate

        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }

        Double fromRate = rates.get(fromCurrency);
        Double toRate = rates.get(toCurrency);

        if (fromRate == null) {
            throw new CurrencyConversionException("Exchange rate not found for: " + fromCurrency);
        }

        if (toRate == null) {
            throw new CurrencyConversionException("Exchange rate not found for: " + toCurrency);
        }

        // Convert from source currency to USD, then to target currency
        return toRate / fromRate;
    }
}
