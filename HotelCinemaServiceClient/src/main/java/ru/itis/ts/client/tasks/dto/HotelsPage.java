package ru.itis.ts.client.tasks.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelsPage {

    private List<HotelDto> hotels;

    private Integer totalPages;
}


