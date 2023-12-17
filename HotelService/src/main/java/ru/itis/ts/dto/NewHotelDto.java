package ru.itis.ts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(name = "New Hotel", description = "Отель для добавления")
public class NewHotelDto {
    @Schema(description = "название отеля", example = "Волжанка", maxLength = 255)
    @NotEmpty
    @NotNull
    private String title;

    @Schema(description = "адрес отеля", example = "улица Ленина, дом 8", maxLength = 500, minLength = 5)
    @NotEmpty
    @NotNull
    @Length(min = 5, max = 500)
    private String address;

    @Schema(description = "город, в котором находится отель", example = "Смоленск", maxLength = 255)
    @NotEmpty
    @NotNull
    private String city;

    @Schema(description = "количество звёзд отлея", example = "5")
    @NotNull
    @Min(value = 0, message = "Stars must be bigger than 0")
    @Max(value = 5, message = "Stars must be less than 5")
    @Digits(integer = 1, fraction = 0)
    private int stars;

    @Schema(description = "количество номеров в отеле", example = "10")
    @NotNull
    @Min(value = 1, message = "Rooms must be bigger than 1")
    @Digits(integer = 3, fraction = 0)
    private int rooms;

}

