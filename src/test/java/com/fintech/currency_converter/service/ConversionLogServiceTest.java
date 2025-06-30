package com.fintech.currency_converter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.fintech.currency_converter.model.ConversionLog;
import com.fintech.currency_converter.repository.ConversionLogRepository;

@DataJpaTest
public class ConversionLogServiceTest {
    
    @Autowired
    private ConversionLogRepository repository;

    @Test
    void testSaveAndFindAll() {
        // Arrange: create a ConversionLog
        ConversionLog log = new ConversionLog();
        log.setFromCurrency("USD");
        log.setToCurrency("EUR");
        log.setOriginalAmount(100.0);
        log.setConvertedAmount(92.0);
        log.setExchangeRate(0.92);
        log.setTimestamp(LocalDateTime.now());

        // Act: save the log
        repository.save(log);

        // Assert: retrieve all logs and check
        List<ConversionLog> logs = repository.findAll();
        assertEquals(1, logs.size());
        ConversionLog saved = logs.get(0);
        assertEquals("USD", saved.getFromCurrency());
        assertEquals("EUR", saved.getToCurrency());
        assertEquals(100.0, saved.getOriginalAmount());
        assertEquals(92.0, saved.getConvertedAmount());
        assertEquals(0.92, saved.getExchangeRate());
        assertNotNull(saved.getTimestamp());
    }
}
