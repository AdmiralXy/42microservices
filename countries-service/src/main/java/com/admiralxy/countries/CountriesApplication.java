package com.admiralxy.countries;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class CountriesApplication {

    @Value("${spring.application.third-party-api}")
    private String thirdPartyApi;

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }

    @GetMapping("/countries-management/countries/{country-name}")
    public ResponseEntity<?> getByCountry(@PathVariable("country-name") String countryName) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(thirdPartyApi + countryName, String.class);
        } catch (RestClientException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
