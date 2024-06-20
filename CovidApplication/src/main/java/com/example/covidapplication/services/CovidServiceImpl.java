package com.example.covidapplication.services;

import com.example.covidapplication.clients.CovidClient;
import com.example.covidapplication.entries.CovidStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidServiceImpl implements CovidService {

    @Autowired
    private List<CovidClient> clients;
    @Override
    public List<CovidStatistic> getAll() {
        return clients.stream().flatMap(covidClient -> covidClient.getAll().stream()).collect(Collectors.toList());

    }
}
