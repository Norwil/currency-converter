# Currency Converter API

A Spring Boot-based REST API for currency conversion that utilizes the FreeCurrency API for real-time exchange rates.

## Features

- Real-time currency conversion
- Support for multiple currencies
- RESTful API endpoints
- Error handling and validation
- Conversion history logging with JPA

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database Engine
- Maven

## API Endpoints

### Convert Currency (POST)
```http
POST /api/currency/convert
Content-Type: application/json

{
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "amount": 100.00
}