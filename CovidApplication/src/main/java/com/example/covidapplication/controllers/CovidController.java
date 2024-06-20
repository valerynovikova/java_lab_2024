package com.example.covidapplication.controllers;

import com.example.covidapplication.entries.CovidStatistic;
import com.example.covidapplication.services.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/covid-managment")
public class CovidController {
    @Autowired
    private CovidService covidService;
    @GetMapping("/records")
    public List<CovidStatistic> getAll(){
        return covidService.getAll();

    }
}
