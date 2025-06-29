package WeatherPick.weatherpick.domain.place.controller;

import WeatherPick.weatherpick.domain.place.dto.NaverPlaceResponseDto;
import WeatherPick.weatherpick.domain.place.service.NaverPlaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places")
public class NaverPlaceController {

    private final NaverPlaceService naverPlaceService;

    public NaverPlaceController(NaverPlaceService naverPlaceService) {
        this.naverPlaceService = naverPlaceService;
    }

    @GetMapping("/search")
    public NaverPlaceResponseDto searchPlaces(@RequestParam String query) {
        return naverPlaceService.searchPlaces(query);
    }
} 