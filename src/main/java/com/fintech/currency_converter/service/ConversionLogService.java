package com.fintech.currency_converter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fintech.currency_converter.model.ConversionLog;
import com.fintech.currency_converter.repository.ConversionLogRepository;




@Service
public class ConversionLogService {

    private final ConversionLogRepository repository;

    public ConversionLogService(ConversionLogRepository repository) {
        this.repository = repository;
    }

    public ConversionLog save(ConversionLog log) {
        return repository.save(log);
    }

    public List<ConversionLog> findAll() {
        return repository.findAll();
    }
    
}
