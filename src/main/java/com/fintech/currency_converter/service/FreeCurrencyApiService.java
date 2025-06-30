package com.fintech.currency_converter.service;

import com.fintech.currency_converter.exception.CurrencyConversionException;
import com.fintech.currency_converter.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class FreeCurrencyApiService {

    private final RestTemplate restTemplate;
    @Value("${freecurrency.api.url}")
    private String apiUrl;
    @Value("${freecurrency.api.key}")
    private String apiKey;

    private static final List<String> SUPPORTED_CURRENCIES = Arrays.asList("EUR", "USD", "TRY", "PLN", "GBP");

    public FreeCurrencyApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getExchangeRates() {
        try {
            String url = String.format("%s?apikey=%s&currencies=%s",
                apiUrl, apiKey, String.join(",", SUPPORTED_CURRENCIES));

            ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

            if (response == null || response.getData() == null) {
                throw new CurrencyConversionException("No exchange rate data received from API");
            }

            return response.getData();
        } catch (RestClientException e) {
            throw new CurrencyConversionException("Failed to fetch exchange rates from API", e);
        }
    }

    public boolean isSupportedCurrency(String currency) {
        return SUPPORTED_CURRENCIES.contains(currency.toUpperCase());
    }

    public List<String> getSupportedCurrencies() {
        return SUPPORTED_CURRENCIES;
    }


}
