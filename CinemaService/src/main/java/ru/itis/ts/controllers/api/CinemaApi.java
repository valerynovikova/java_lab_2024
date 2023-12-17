package ru.itis.ts.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.ts.dto.NewCinemaDto;
import ru.itis.ts.dto.StandardResponseDto;
import ru.itis.ts.dto.CinemaDto;
import ru.itis.ts.dto.CinemaPage;
import ru.itis.ts.validation.dto.ValidationErrorsDto;

import jakarta.validation.Valid;

@Tags(value =
@Tag(name = "Cinema"))
@RequestMapping("/api/cinema")
public interface CinemaApi {

    @Operation(summary = "Получение списка кинотеатров", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CinemaPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<CinemaPage> getCinemas(@Parameter(description = "Номер страницы", example = "1")
                                           @RequestParam("page") Integer page);

    @Operation(summary = "Получение списка кинотеатров в городе", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CinemaPage.class))
                    })
    })
    @GetMapping("/city")
    ResponseEntity<CinemaPage> getCinemas(@Parameter(description = "Номер страницы", example = "1")
                                           @RequestParam("page") Integer page,
                                           @RequestParam("city") String city);

    @Operation(summary = "Получение информации о кинотеатре", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CinemaDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Кинотеатр не найден",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/{cinema-id}")
    ResponseEntity<CinemaDto> getCinema(@Parameter(description = "Идентификатор кинотеатра", example = "1")
                                        @PathVariable("cinema-id") Long taskId);

    @Operation(summary = "Добавление кинотеатра", description = "Доступно только менеджерам")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача добавлена успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CinemaDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<CinemaDto> addCinema(@RequestBody @Valid NewCinemaDto newTask);

}
