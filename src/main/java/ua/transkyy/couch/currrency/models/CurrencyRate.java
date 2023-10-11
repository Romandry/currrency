package ua.transkyy.couch.currrency.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate {

    @JsonProperty("txt")
    private String textCode;

    @JsonProperty("cc")
    private String currencyCode;

    @JsonProperty("rate")
    private BigDecimal rate;
}
