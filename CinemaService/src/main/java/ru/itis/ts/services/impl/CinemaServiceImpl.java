package ru.itis.ts.services.impl;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.ts.dto.CinemaDto;
import ru.itis.ts.dto.CinemaPage;
import ru.itis.ts.dto.NewCinemaDto;
import ru.itis.ts.exceptions.RestException;
import ru.itis.ts.models.Cinema;
import ru.itis.ts.repositories.CinemaRepository;
import ru.itis.ts.services.CinemaService;

import java.util.Optional;

import static ru.itis.ts.dto.CinemaDto.from;
import static ru.itis.ts.util.PageUtils.DEFAULT_PAGE_SIZE;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {
    CinemaRepository cinemasRepository;

    @Override
    public CinemaPage getCinema(Integer page) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Cinema> cinemas = cinemasRepository.findAll(request);

        return CinemaPage.builder()
                .cinemas(from(cinemas.getContent()))
                .totalPages(cinemas.getTotalPages())
                .build();
    }

    @Override
    public CinemaPage getCinema(Integer page, String city) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Cinema> cinemas = cinemasRepository.findAllByCity(request, city);
        return CinemaPage.builder()
                .cinemas(from(cinemas.getContent()))
                .totalPages(cinemas.getTotalPages())
                .build();
    }

    @Override
    public CinemaDto getCinema(Long cinemaId) {
        Cinema cinema = cinemasRepository.findById(cinemaId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Cinema with id <" + cinemaId + "> not found"));
        return from(cinema);
    }

    @Override
    public CinemaDto getCinema(String title) {
        Optional<Cinema> cinema = cinemasRepository.findByTitle(title);

        return cinema.map(CinemaDto::from).orElse(null);
    }

    @Transactional
    @Override
    public CinemaDto addCinema(NewCinemaDto newCinema) {
        CinemaDto dto = this.getCinema(newCinema.getTitle());
        if (dto != null) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Cinema with title <" + newCinema.getTitle() + "> already exists");
        }
        Cinema cinema = Cinema.builder()
                .title(newCinema.getTitle())
                .address(newCinema.getAddress())
                .city(newCinema.getCity())
                .build();

        cinemasRepository.save(cinema);

        return from(cinema);
    }
}

