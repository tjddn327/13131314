package WeatherPick.weatherpick.controller;


import WeatherPick.weatherpick.domain.place.dto.NaverPlaceDto;
import WeatherPick.weatherpick.domain.place.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/naver")
@RequiredArgsConstructor
public class NaverApiController {
    private final NaverApiService naverApiService;

    @PostMapping("/searchPlace")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, List<NaverPlaceDto>>> searchPlace(@RequestBody Map<String, String> request) {
        String placeName = request.get("placeName");
        List<NaverPlaceDto> places = naverApiService.searchPlace(placeName);
        return ResponseEntity.ok(Map.of("places", places));
    }
}
