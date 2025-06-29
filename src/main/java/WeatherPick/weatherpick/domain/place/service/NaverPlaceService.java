package WeatherPick.weatherpick.domain.place.service;

import WeatherPick.weatherpick.domain.place.dto.NaverPlaceResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class NaverPlaceService {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    public NaverPlaceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NaverPlaceResponseDto searchPlaces(String query) {
        String url = "https://naveropenapi.apigw.ntruss.com/map-place/v1/search";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        String fullUrl = UriComponentsBuilder.fromUriString(url)
                .queryParam("query", query)
                .queryParam("coordinate", "126.9267862,35.1473526")
                .build()
                .toUriString();

        ResponseEntity<NaverPlaceResponseDto> response = restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                NaverPlaceResponseDto.class
        );

        return response.getBody();
    }
} 