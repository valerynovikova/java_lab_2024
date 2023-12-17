package ru.itis.ts.services;


import ru.itis.ts.dto.HotelDto;
import ru.itis.ts.dto.HotelsPage;
import ru.itis.ts.dto.NewHotelDto;

public interface HotelService {
    HotelsPage getHotel(Integer page);

    HotelDto getHotel(Long hotelId);
    HotelDto getHotel(String title);

    HotelsPage getHotel(Integer page, String city);

    HotelDto addHotel(NewHotelDto newHotel);

}

