package ua.transkyy.couch.currrency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyServiceTest {

    private final String PATTERN_DATE = "yyyyMMdd";
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testConvertDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        String formattedDate = now.format(formatter);

        String convertedDate = currencyService.convertDateTime();
        assertEquals(formattedDate, convertedDate);
    }

    @Test
    public void testGetCurrencyData() {

    }

    @Test
    public void testCalculateRate() {

    }

}
