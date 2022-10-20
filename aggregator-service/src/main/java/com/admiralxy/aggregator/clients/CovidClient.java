package com.admiralxy.aggregator.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "covid-service")
public interface CovidClient {

    @GetMapping("/covid-management/countries/{country-name}")
    ResponseEntity<?> getByCountry(@PathVariable("country-name") String countryName);
}
