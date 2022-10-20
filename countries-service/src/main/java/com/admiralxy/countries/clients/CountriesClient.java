package com.admiralxy.countries.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient("countries-eureka-client")
public interface CountriesClient {
    ResponseEntity<?> getByCountry(String countryName);
}
