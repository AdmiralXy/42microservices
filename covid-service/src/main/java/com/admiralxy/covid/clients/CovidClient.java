package com.admiralxy.covid.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient("covid-eureka-client")
public interface CovidClient {
    ResponseEntity<?> getByCountry(String countryName);
}
