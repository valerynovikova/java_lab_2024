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
public class CinemasPage {

    private List<CinemaDto> cinemas;

    private Integer totalPages;
}


