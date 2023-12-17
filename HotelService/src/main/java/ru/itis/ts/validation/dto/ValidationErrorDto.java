package ru.itis.ts.validation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Ошибка валидации")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ValidationErrorDto {

    @Schema(description = "Поле, в котором возникло исключение", example = "title")
    private String field;

    @Schema(description = "Сообщение об ошибке", example = "title must not be null")
    private String message;

    @Schema(description = "Какое значение было получено от клиента", example = "null")
    private String rejectedValue;
}
