package com.example.covidapplication.clients;

import com.example.covidapplication.entries.CovidStatistic;

import java.util.List;

public interface CovidClient {
    List<CovidStatistic> getAll();
}
