package ru.itis.ts.client.tasks.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.ts.client.tasks.dto.HotelsPage;


@FeignClient(name = "hotels-service", url = "${feign.hotels-service.url}")
public interface HotelService {

    @GetMapping(value = "/api/hotels/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    HotelsPage getHotels(@RequestParam("page") Integer page,
                         @RequestParam("api-key") String apiKey,
                         @RequestParam("city") String city);
}

