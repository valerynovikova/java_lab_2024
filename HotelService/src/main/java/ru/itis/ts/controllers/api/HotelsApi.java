package ru.itis.ts.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.ts.dto.HotelDto;
import ru.itis.ts.dto.HotelsPage;
import ru.itis.ts.dto.NewHotelDto;
import ru.itis.ts.dto.StandardResponseDto;
import ru.itis.ts.validation.dto.ValidationErrorsDto;


@Tags(value =
@Tag(name = "Hotels"))
@RequestMapping("/api/hotels")
public interface HotelsApi {
    @Operation(summary = "Получение списка отелей", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelsPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<HotelsPage> getHotel(@Parameter(description = "Номер страницы", example = "1")
                                        @RequestParam("page") Integer page);

    @Operation(summary = "Получение списка отелей в городе", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelsPage.class))
                    })
    })
    @GetMapping("/city")
    ResponseEntity<HotelsPage> getCinemas(@Parameter(description = "Номер страницы", example = "1")
                                          @RequestParam("page") Integer page,
                                          @RequestParam("city") String city);

    @Operation(summary = "Получение информации об отелях", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос обработан успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Отель не найден",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/{cinema-id}")
    ResponseEntity<HotelDto> getHotel(@Parameter(description = "Идентификатор отеля", example = "1")
                                      @PathVariable("cinema-id") Long hotelId);

    @Operation(summary = "Добавление отеля", description = "Доступно только менеджерам")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача добавлена успешно",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<HotelDto> addHotel(@RequestBody @Valid NewHotelDto newHotel);
}

