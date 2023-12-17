package ru.itis.ts.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import ru.itis.ts.controllers.api.CinemaApi;
import ru.itis.ts.dto.CinemaDto;
import ru.itis.ts.dto.CinemaPage;
import ru.itis.ts.dto.NewCinemaDto;
import ru.itis.ts.services.CinemaService;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CinemaController implements CinemaApi {
    CinemaService cinemasService;

    @Override
    public ResponseEntity<CinemaPage> getCinemas(Integer page) {
        return ResponseEntity.ok(cinemasService.getCinema(page));
    }

    @Override
    public ResponseEntity<CinemaPage> getCinemas(Integer page, String city) {
        return ResponseEntity.ok(cinemasService.getCinema(page, city));
    }

    @Override
    public ResponseEntity<CinemaDto> getCinema(Long cinemaId) {
        return ResponseEntity.ok(cinemasService.getCinema(cinemaId));
    }

    @Override
    public ResponseEntity<CinemaDto> addCinema(NewCinemaDto newCinema) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cinemasService.addCinema(newCinema));
    }
}
