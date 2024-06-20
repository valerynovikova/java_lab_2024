package com.example.covidapplication.clients;

import com.example.covidapplication.entries.Covid19ApiRecord;
import com.example.covidapplication.entries.CovidStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Covid19ApiClentRestTemplateImpl implements CovidClient{
    @Value("${covid.api.url}")
    private String url;

    @Autowired
    private RestTemplate client;
    @Override
    public List<CovidStatistic> getAll() {
        Covid19ApiRecord[] response = client.getForEntity(url, Covid19ApiRecord[].class).getBody();
        List<Covid19ApiRecord> records = Arrays.asList(Objects.requireNonNull(response));
        return records.stream()
                .map(record ->CovidStatistic.builder()
                        .country(record.getCountryCode())
                        .dateTime(record.getDate())
                        .from("Covid19Api")
                        .recovered(Integer.valueOf(record.getRecovered()))
                        .build())
                .collect(Collectors.toList());
    }
}
