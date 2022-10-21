package com.admiralxy.aggregator.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "country-service")
public interface CountryClient {

    @GetMapping("/countries-management/countries/{country-name}")
    ResponseEntity<String> getByCountry(@PathVariable("country-name") String countryName);

}
