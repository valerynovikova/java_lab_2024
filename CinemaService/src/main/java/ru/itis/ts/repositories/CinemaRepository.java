package ru.itis.ts.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ts.models.Cinema;

import java.util.Optional;


public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findByTitle(String title);

    Page<Cinema> findAllByCity(PageRequest request, String city);

}


