package ru.itis.ts.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Tasks Endpoint is works")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class TasksIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /tasks")
    public class GetTasks {

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_page_with_tasks() throws Exception {
            mockMvc.perform(get("/api/tasks").param("page", "0"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.tasks[0].title", is("Посадка деревьев")))
                    .andExpect(jsonPath("$.tasks[1].title", is("Ремонт общежитий")));
        }
    }

    @Nested
    @DisplayName("POST /tasks")
    public class PostTasks {

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_task() throws Exception {
            mockMvc.perform(post("/api/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"title\": \"Новая задача!\",\n" +
                                    "  \"description\": \"Обязательно выполнять!\",\n" +
                                    "  \"start\": \"02.02.2022\",\n" +
                                    "  \"finish\": \"02.03.2022\"\n" +
                                    "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(3)));
        }

        @Test
        public void return_bad_request_on_incorrect_task() throws Exception {
            mockMvc.perform(post("/api/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"description\": \"Обязательно выполнять!\",\n" +
                                    "  \"start\": \"02.02.2022\",\n" +
                                    "  \"finish\": \"02.03.2022\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors[0].field", is("title")));
        }
    }

}