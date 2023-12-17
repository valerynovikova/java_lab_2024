package ru.itis.ts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Страница с отелями")
public class HotelsPage {

    @Schema(description = "Список отелей")
    private List<HotelDto> hotels;

    @Schema(description = "Общее количество страниц с отелями", example = "10")
    private Integer totalPages;
}
