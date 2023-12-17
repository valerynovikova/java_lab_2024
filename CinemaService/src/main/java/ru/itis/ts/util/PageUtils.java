package ru.itis.ts.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PageUtils {

    public static int DEFAULT_PAGE_SIZE;

    @Value("${tasks.page.size}")
    public void setDefaultPageSize(int defaultPageSize) {
        DEFAULT_PAGE_SIZE = defaultPageSize;
    }

}
