package ru.itis.ts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ts.models.Hotel;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Hotel", description = "отель")
public class HotelDto {
    @Schema(description = "идентификатор отеля", example = "1")
    private Long id;

    @Schema(description = "название отеля", example = "Славянка", maxLength = 255)
    private String title;

    @Schema(description = "описание отеля", example = "улица Можайского, дом 37", maxLength = 500)
    private String address;

    @Schema(description = "город, в котором находится отель", example = "Смоленск", maxLength = 255)
    private String city;

    @Schema(description = "количество звезд", example = "4")
    private int stars;

    @Schema(description = "количество номеров'", example = "10")
    private int rooms;

    public static HotelDto from(Hotel hotel) {

        return HotelDto.builder()
                .id(hotel.getId())
                .title(hotel.getTitle())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .rooms(hotel.getRooms())
                .stars(hotel.getStars())
                .build();
    }

    public static List<HotelDto> from(List<Hotel> hotels) {
        return hotels.stream()
                .map(HotelDto::from)
                .collect(Collectors.toList());
    }


}

