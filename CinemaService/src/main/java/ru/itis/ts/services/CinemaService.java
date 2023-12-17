package ru.itis.ts.services;


import ru.itis.ts.dto.CinemaDto;
import ru.itis.ts.dto.CinemaPage;
import ru.itis.ts.dto.NewCinemaDto;

public interface CinemaService {
    CinemaPage getCinema(Integer page);

    CinemaPage getCinema(Integer page, String city);

    CinemaDto getCinema(String title);

    CinemaDto getCinema(Long cinemaId);

    CinemaDto addCinema(NewCinemaDto newCinema);

}

