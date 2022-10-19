package com.admiralxy.countries.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("countries-eureka-client")
public interface CountriesClient {
    String getCountry(String countryName);
}
