package ua.transkyy.couch.currrency.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyConvertControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static String CURRENCY_IN = "EUR";
    private static String CURRENCY_OUT = "USD";

    @Test
    public void testConvert() throws Exception {
        mockMvc.perform(get("/convert")
                .param("in", CURRENCY_IN)
                .param("out", CURRENCY_OUT))
                .andExpect(status().isOk())
                .andExpect(content().string("DONE EUR USD"));

    }

//    @Test
//    public void testConvertDateTime
}
