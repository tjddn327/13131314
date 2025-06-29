package WeatherPick.weatherpick.controller;


import WeatherPick.weatherpick.domain.weather.dto.WeatherDto;
import WeatherPick.weatherpick.domain.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public List<WeatherDto> getMorningWeather() {
        return weatherService.fetchWeatherData();
    }
}
