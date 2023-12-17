package ru.itis.ts.client.tasks.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ts.client.tasks.dto.CinemasPage;
import ru.itis.ts.client.tasks.dto.CityPage;
import ru.itis.ts.client.tasks.dto.HotelsPage;
import ru.itis.ts.client.tasks.service.CinemaService;
import ru.itis.ts.client.tasks.service.HotelService;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class CityController {

    CinemaService cinemasService;
    HotelService hotelsService;

    @GetMapping("/info")
    public ResponseEntity<CityPage> getInfo(@RequestParam("city") String city,
                                            @RequestParam("hotels_page") Integer hotelsPageNumber,
                                            @RequestParam("cinemas_page") Integer cinemasPageNumber) {
        CinemasPage cinemasPage = cinemasService.getCinemas(cinemasPageNumber, "cinema-key", city);
        HotelsPage hotelsPage = hotelsService.getHotels(hotelsPageNumber, "hotels-key", city);

        return ResponseEntity.ok(CityPage.builder()
                .cinemasPage(cinemasPage)
                .hotelsPage(hotelsPage)
                .build());
    }
}




