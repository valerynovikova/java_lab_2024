package ru.itis.ts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(name = "New Cinema", description = "Кинотеатр для добавления")
public class NewCinemaDto {
    @Schema(description = "название кинотеатра", example = "Каро", maxLength = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String title;

    @Schema(description = "адрес кинотеатра", example = "улица Ленина, дом 8", maxLength = 500, minLength = 5)
    @NotEmpty
    @NotBlank
    @NotNull
    @Length(min = 5, max = 500)
    private String address;

    @Schema(description = "город, в котором находится кинотеатр", example = "Смоленск", maxLength = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String city;

}
