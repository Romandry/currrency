package ua.transkyy.couch.currrency.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ua.transkyy.couch.currrency.models.CurrencyRate;
import ua.transkyy.couch.currrency.service.CurrencyService;

@RestController
public class CurrencyConvertController {
    private final String BASE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?";
    private RestTemplate restTemplate = new RestTemplate();

    private final CurrencyService currencyService;

    public CurrencyConvertController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/convert")
    public String convert(
            @RequestParam(name = "in") String currencyIn,
            @RequestParam(name = "out") String currencyOut
    ) throws IOException {

        String formattedDate = currencyService.convertDateTime();

        ResponseEntity<String> responseIn = restTemplate.exchange(
                BASE_URL + "valcode=" + currencyIn + "&json&date=" + formattedDate,
                HttpMethod.GET,
                null,
                String.class
        );
        ResponseEntity<String> responseOut = restTemplate.exchange(
                BASE_URL + "valcode=" + currencyOut + "&json&date=" + formattedDate,
                HttpMethod.GET,
                null,
                String.class
        );

        if(responseIn.getStatusCode() == HttpStatus.OK) {

            CurrencyRate currencyRateIn = currencyService.getCurrencyData(responseIn);



            return responseOut.getBody();
        }else {
            return "Error " + responseOut.getStatusCode();
        }



    }

}
