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
import ru.itis.ts.dto.HotelDto;
import ru.itis.ts.dto.HotelsPage;
import ru.itis.ts.dto.NewHotelDto;
import ru.itis.ts.exceptions.RestException;
import ru.itis.ts.models.Hotel;
import ru.itis.ts.repositories.HotelRepository;
import ru.itis.ts.services.HotelService;


import java.util.Optional;

import static ru.itis.ts.util.PageUtils.DEFAULT_PAGE_SIZE;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    HotelRepository hotelRepository;

    @Override
    public HotelsPage getHotel(Integer page) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Hotel> hotels = hotelRepository.findAll(request);

        return HotelsPage.builder()
                .hotels(from(hotels.getContent()))
                .totalPages(hotels.getTotalPages())
                .build();
    }

    @Override
    public HotelDto getHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Hotel with id <" + hotelId + "> not found"));
        return from(hotel);
    }

    @Override
    public HotelsPage getHotel(Integer page, String city) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Hotel> cinemas = hotelRepository.findAllByCity(request, city);
        return HotelsPage.builder()
                .hotels(from(cinemas.getContent()))
                .totalPages(cinemas.getTotalPages())
                .build();
    }

    @Override
    public HotelDto getHotel(String title) {
        Optional<Hotel> cinema = hotelRepository.findByTitle(title);

        return cinema.map(HotelDto::from).orElse(null);
    }

    @Transactional
    @Override
    public HotelDto addHotel(NewHotelDto newHotel) {
        HotelDto dto = this.getHotel(newHotel.getTitle());
        if (dto != null) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Hotel with title <" + newHotel.getTitle() + "> already exists");
        }
        Hotel hotel = Hotel.builder()
                .title(newHotel.getTitle())
                .address(newHotel.getAddress())
                .city(newHotel.getCity())
                .stars(newHotel.getStars())
                .rooms(newHotel.getRooms())
                .build();

        hotelRepository.save(hotel);

        return from(hotel);

    }
}
