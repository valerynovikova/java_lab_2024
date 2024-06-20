package com.example.covidapplication.entries;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TheVirusTrackerRecord {
    @JsonProperty("countrycode")
    private String countryCode;
    private String date;
    private String recovered;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDate() {
        return date;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
