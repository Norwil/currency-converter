package com.fintech.currency_converter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.currency_converter.model.ConversionLog;
import com.fintech.currency_converter.service.ConversionLogService;

@RestController
@RequestMapping("/api/logs")
public class ConversionLogController {
    private final ConversionLogService service;

    public ConversionLogController(ConversionLogService service) {
        this.service = service;
    }

    @PostMapping
    public ConversionLog saveLog(@RequestBody ConversionLog log) {
        return service.save(log);
    }

    @GetMapping
    public List<ConversionLog> getAllLogs() {
        return service.findAll();
    }
}
