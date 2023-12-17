package ru.itis.ts.services.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import ru.itis.ts.dto.CinemaPage;
import ru.itis.ts.models.Cinema;
import ru.itis.ts.repositories.CinemaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("TasksService is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class TasksServiceImplTest {

    @MockBean
    private CinemaRepository tasksRepository;

    @Autowired
    private CinemaServiceImpl tasksService;

    @Value("${tasks.page.size}")
    private int pageSizeForTests;

    @Nested
    @DisplayName("getTasks() ")
    public class GetTasks {

        @BeforeEach
        public void setUp() {
            PageRequest request = PageRequest.of(0, pageSizeForTests, Sort.by("id"));

            when(tasksRepository.findAll(request)).thenReturn(
                    new PageImpl<>(TASKS));
        }


        @Test
        public void returns_tasks() throws Exception {
            CinemaPage tasksPage = tasksService.getTasks(0);

            assertEquals(2, tasksPage.getTasks().size());
        }
    }

    private static final Cinema TASK_1 = Cinema.builder()
            .id(1L)
            .title("Task 1")
            .description("Task 1 Description")
            .start(LocalDate.of(2022, 2, 2))
            .finish(LocalDate.of(2022, 3, 2))
            .build();

    private static final Cinema TASK_2 = Cinema.builder()
            .id(2L)
            .title("Task 2")
            .description("Task 2 Description")
            .start(LocalDate.of(2023, 2, 2))
            .finish(LocalDate.of(2023, 3, 2))
            .build();

    private static final List<Cinema> TASKS = Arrays.asList(TASK_1, TASK_2);

}