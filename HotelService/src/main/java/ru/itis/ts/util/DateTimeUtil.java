package ru.itis.ts.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;


public class DateTimeUtil {

    public static final DateTimeFormatter EUROPEAN_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

}
