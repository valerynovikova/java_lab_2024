package com.example.covidapplication.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Covid19ApiRecord {
    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Recovered")
    private String recovered;

    @JsonProperty("Date")
    private String date;
}
