package com.admiralxy.aggregator;

import com.admiralxy.aggregator.clients.AggregatorClient;
import com.admiralxy.aggregator.clients.CovidClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@EnableFeignClients
@SpringBootApplication
public class AggregatorApplication implements AggregatorClient {

    private final CovidClient covidClient;

    @Autowired
    public AggregatorApplication(CovidClient covidClient) {
        this.covidClient = covidClient;
    }

    @GetMapping("/information-management/countries/{country-name}")
    @Override
    public ResponseEntity<?> getByCountry(@PathVariable("country-name") String countryName) {
        try {
            return covidClient.getByCountry(countryName);
        } catch (RestClientException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AggregatorApplication.class, args);
    }
}
