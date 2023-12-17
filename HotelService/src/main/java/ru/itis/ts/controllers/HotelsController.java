package ru.itis.ts.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ts.controllers.api.HotelsApi;
import ru.itis.ts.dto.HotelDto;
import ru.itis.ts.dto.HotelsPage;
import ru.itis.ts.dto.NewHotelDto;
import ru.itis.ts.services.HotelService;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsController implements HotelsApi {
    HotelService hotelService;

    @Override
    public ResponseEntity<HotelsPage> getHotel(Integer page) {
        return ResponseEntity.ok(hotelService.getHotel(page));
    }

    @Override
    public ResponseEntity<HotelsPage> getCinemas(Integer page, String city) {
        return ResponseEntity.ok(hotelService.getHotel(page, city));
    }

    @Override
    public ResponseEntity<HotelDto> getHotel(Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    @Override
    public ResponseEntity<HotelDto> addHotel(NewHotelDto newHotel) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hotelService.addHotel(newHotel));
    }
}

