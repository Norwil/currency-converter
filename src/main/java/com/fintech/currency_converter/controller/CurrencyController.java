package com.fintech.currency_converter.controller;


import com.fintech.currency_converter.exception.CurrencyConversionException;
import com.fintech.currency_converter.model.CurrencyConversionRequest;
import com.fintech.currency_converter.model.CurrencyConversionResponse;
import com.fintech.currency_converter.service.CurrencyService;
import com.fintech.currency_converter.service.FreeCurrencyApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/currency")
@CrossOrigin(origins = "*") // For frontend integration later
public class CurrencyController {

    private final CurrencyService currencyService;
    private final FreeCurrencyApiService freeCurrencyApiService;

    public CurrencyController(CurrencyService currencyService, FreeCurrencyApiService freeCurrencyApiService) {
        this.currencyService = currencyService;
        this.freeCurrencyApiService = freeCurrencyApiService;
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        try {
            CurrencyConversionResponse response = currencyService.convertCurrency(request);
            return ResponseEntity.ok(response);
        } catch (CurrencyConversionException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/rates")
    public ResponseEntity<?> getExchangeRates() {
        try {
            Map<String, Double> rates = freeCurrencyApiService.getExchangeRates();
            return ResponseEntity.ok(rates);
        } catch (CurrencyConversionException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/convert/{from}/{to}/{amount}")
    public ResponseEntity<?> convertCurrencyget(@PathVariable String from,
                                                @PathVariable String to,
                                                @PathVariable Double amount) {
        try {
            CurrencyConversionRequest request = new CurrencyConversionRequest(from, to, amount);
            CurrencyConversionResponse response = currencyService.convertCurrency(request);
            return ResponseEntity.ok(response);
        } catch (CurrencyConversionException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
