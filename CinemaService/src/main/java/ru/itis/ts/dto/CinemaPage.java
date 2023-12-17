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
@Schema(description = "Страница с кинотеатрами")
public class CinemaPage {

    @Schema(description = "Список кинотеатров")
    private List<CinemaDto> cinemas;

    @Schema(description = "Общее количество страниц с кинотеатрами", example = "10")
    private Integer totalPages;
}

