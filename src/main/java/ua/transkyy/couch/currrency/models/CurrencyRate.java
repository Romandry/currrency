package ua.transkyy.couch.currrency.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRate {

    @JsonProperty("txt")
    private String textCode;

    @JsonProperty("cc")
    private String currencyCode;

    @JsonProperty("rate")
    private BigDecimal rate;
}
