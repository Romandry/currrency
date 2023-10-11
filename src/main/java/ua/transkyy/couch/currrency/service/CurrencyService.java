package ua.transkyy.couch.currrency.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.transkyy.couch.currrency.models.CurrencyRate;

@Service
public class CurrencyService {

    private final String PATTERN_DATE = "yyyyMMdd";
    public String convertDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        return now.format(formatter);
    }

    public CurrencyRate getCurrencyData(ResponseEntity response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree((JsonParser) response.getBody());

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrencyCode(jsonNode.get("cc").asText());
        currencyRate.setRate(jsonNode.get("rate").decimalValue());
        currencyRate.setTextCode(jsonNode.get("txt").asText());

        return currencyRate;
    }
}
