package com.example.covidapplication.clients;

import com.example.covidapplication.entries.CovidStatistic;
import com.example.covidapplication.entries.TheVirusTrackerRecord;
import com.example.covidapplication.entries.TheVirusTrackerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TheVirusTrackerClientRestTemplateImpl implements CovidClient {
    @Value("${thevirustracker.url}")
    private String url;

    @Autowired
    private RestTemplate client;
    @Override
    public List<CovidStatistic> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        TheVirusTrackerResponse response = client.exchange(url, HttpMethod.GET, entity, TheVirusTrackerResponse.class).getBody();
        List<TheVirusTrackerRecord> records = Objects.requireNonNull(response).getData();
        return records.parallelStream()
                .map(record -> CovidStatistic.builder()
                        .country(record.getCountryCode())
                        .dateTime(record.getDate())
                        .from("TheVirusTracker")
                        .recovered(Integer.parseInt(record.getRecovered()))
                        .build())
                .collect(Collectors.toList());

    }
}
