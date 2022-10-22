package com.admiralxy.aggregator;

import com.admiralxy.aggregator.clients.CountryClient;
import com.admiralxy.aggregator.clients.CovidClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@SpringBootApplication
public class AggregatorApplication {

    private final CovidClient covidClient;

    private final CountryClient countryClient;

    @Autowired
    public AggregatorApplication(CovidClient covidClient, CountryClient countryClient) {
        this.covidClient = covidClient;
        this.countryClient = countryClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(AggregatorApplication.class, args);
    }

    @GetMapping("/information-management/countries/{country-name}")
    public ResponseEntity<String> getByCountry(@PathVariable("country-name") String countryName) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            String covid = covidClient.getByCountry(countryName).getBody();
            String country = countryClient.getByCountry(countryName).getBody();

            ObjectNode covidNode = (ObjectNode) mapper.readTree(covid);
            ArrayNode countryNode = (ArrayNode) mapper.readTree(country);

            JsonNode complexNode = mapper.readTree("{\"covid\": " + covidNode + ", \"country\": " + countryNode + "}");

            return ResponseEntity.ok(complexNode.toString());

        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
