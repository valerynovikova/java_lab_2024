package ru.itis.ts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Context is loaded")
class TasksServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
