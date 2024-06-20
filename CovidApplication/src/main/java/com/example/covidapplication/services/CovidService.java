package com.example.covidapplication.services;

import com.example.covidapplication.entries.CovidStatistic;

import java.util.List;

public interface CovidService {
    List<CovidStatistic> getAll();
}
