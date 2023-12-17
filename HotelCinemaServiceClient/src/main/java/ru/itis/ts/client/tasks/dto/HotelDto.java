package ru.itis.ts.client.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {
    private Long id;

    private String title;

    private String address;

    private String city;

    private int stars;

    private int rooms;

}
