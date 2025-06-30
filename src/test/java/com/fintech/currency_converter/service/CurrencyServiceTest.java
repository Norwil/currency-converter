package com.fintech.currency_converter.service;

import com.fintech.currency_converter.exception.CurrencyConversionException;
import com.fintech.currency_converter.model.CurrencyConversionRequest;
import com.fintech.currency_converter.model.CurrencyConversionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyServiceTest {

    // Test implementation of FreeCurrencyApiService
    private static class TestCurrencyApiService extends FreeCurrencyApiService {
        private final Map<String, Double> rates;
    
        public TestCurrencyApiService(Map<String, Double> rates) {
            super(new RestTemplate()); // Pass dummy values
            this.rates = rates;
        }
    
        @Override
        public Map<String, Double> getExchangeRates() {
            return rates;
        }

        @Override
        public boolean isSupportedCurrency(String currency) {
            return rates.containsKey(currency);
        }
    }
    
    @Test
    void convertCurrency_validRequest_returnCorrectResponse() {
        // Setup test service with fixed rates
        FreeCurrencyApiService testService = new TestCurrencyApiService(
                Map.of("USD", 1.0, "EUR", 0.92, "GBP", 0.79)
        );
        CurrencyService service = new CurrencyService(testService);
    
        CurrencyConversionRequest request = new CurrencyConversionRequest("USD", "EUR", 100.0);
    
        CurrencyConversionResponse response = service.convertCurrency(request);
    
        assertAll(
                () -> assertEquals("USD", response.getFromCurrency()),
                () -> assertEquals("EUR", response.getToCurrency()),
                () -> assertEquals(100.0, response.getOriginalAmount()),
                () -> assertEquals(92.0, response.getConvertedAmount()),
                () -> assertEquals(0.92, response.getExchangeRate())
        );
    }

    @Test
    void convertCurrency_invalidFromCurrency_throwsException() {
        // Setup test service with fixed rates
        FreeCurrencyApiService testService = new TestCurrencyApiService(
                Map.of("USD", 1.0, "EUR", 0.92, "GBP", 0.79)
        );

        CurrencyService service = new CurrencyService(testService);
        CurrencyConversionRequest request = new CurrencyConversionRequest("", "EUR", 100.0);

        Exception exception = assertThrows(CurrencyConversionException.class,
                () -> service.convertCurrency(request));

        assertEquals("From currency is required", exception.getMessage());
    }

    @Test
    void convertCurrency_zeroAmount_throwsException() {
        // Setup test service with fixed rates
        FreeCurrencyApiService testService = new TestCurrencyApiService(
                Map.of("USD", 1.0, "EUR", 0.92, "GBP", 0.79)
        );
        CurrencyService service = new CurrencyService(testService);
        CurrencyConversionRequest request = new CurrencyConversionRequest("USD", "EUR", 0.0);

        Exception exception = assertThrows(CurrencyConversionException.class,
                () -> service.convertCurrency(request));

        assertEquals("Amount must be greater than 0", exception.getMessage());
    }

    @Test
    void convertCurrency_unsupportedCurrency_throwsException() {
        // Setup test service with limited rates
        FreeCurrencyApiService testService = new TestCurrencyApiService(
                Map.of("USD", 1.0)
        );
        CurrencyService service = new CurrencyService(testService);

        CurrencyConversionRequest request = new CurrencyConversionRequest("USD", "XYZ", 100.0);

        Exception exception = assertThrows(CurrencyConversionException.class,
                () -> service.convertCurrency(request));

        assertEquals("Unsupported to currency: XYZ", exception.getMessage());

    }

}
