package com.admiralxy.aggregator.clients;

import org.springframework.http.ResponseEntity;

public interface AggregatorClient {
    ResponseEntity<?> getByCountry(String countryName);
}
