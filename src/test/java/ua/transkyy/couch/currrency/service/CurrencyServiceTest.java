package ua.transkyy.couch.currrency.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.transkyy.couch.currrency.models.CurrencyRate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest
@AutoConfigureMockMvc
class CurrencyServiceTest {

    private final String PATTERN_DATE = "yyyyMMdd";
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    private static String CURRENCY_IN = "EUR";
    private static String CURRENCY_OUT = "USD";
    private static String CURRENCY_TEXT = "Євро";
    private static String CURRENCY_DATE = "20231011";
    private static BigDecimal CURRENCY_RATE = BigDecimal.valueOf(38.6049);

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
    public void testCalculateRate() throws Exception {

        CurrencyRate currencyRate = new CurrencyRate(
                CURRENCY_TEXT,
                CURRENCY_IN,
                CURRENCY_RATE
        );

        List<CurrencyRate> currencyRateList = new ArrayList<>();
        currencyRateList.add(currencyRate);

        String expectedJson = objectMapper.writeValueAsString(currencyRateList);

        String responseJson = mockMvc.perform(get("/convert")
                        .param("in", CURRENCY_IN)
                        .param("out", CURRENCY_OUT)
                        .param("date", CURRENCY_DATE))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedJson, responseJson, false);
    }

}
