package com.admiralxy.countries;

import com.admiralxy.countries.clients.CountriesClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@SpringBootApplication
public class CountriesApplication implements CountriesClient {

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }

    @GetMapping("/countries-management/countries/{country-name}")
    @Override
    public String getCountry(@PathVariable("country-name") String countryName) {
        return "Country: " + countryName;
    }
}
