package ru.itis.ts.client.tasks.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.ts.client.tasks.dto.CinemasPage;


@FeignClient(name = "cinemas-service", url = "${feign.cinemas-service.url}")
public interface CinemaService {

    @GetMapping(value = "/api/cinemas/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    CinemasPage getCinemas(@RequestParam("page") Integer page,
                           @RequestParam("api-key") String apiKey,
                           @RequestParam("city") String city);
}
