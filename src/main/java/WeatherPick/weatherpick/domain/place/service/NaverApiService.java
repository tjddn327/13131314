package WeatherPick.weatherpick.domain.place.service;

import WeatherPick.weatherpick.domain.place.dto.NaverPlaceDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverApiService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    public List<NaverPlaceDto> searchPlace(String placeName) {
        String url = UriComponentsBuilder.fromHttpUrl("https://openapi.naver.com/v1/search/local.json")
                .queryParam("query", placeName)
                .queryParam("display", 30)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode itemsNode = rootNode.get("items");
            
            if (itemsNode != null && itemsNode.isArray()) {
                List<NaverPlaceDto> places = new ArrayList<>();
                for (JsonNode itemNode : itemsNode) {
                    NaverPlaceDto place = objectMapper.treeToValue(itemNode, NaverPlaceDto.class);
                    places.add(place);
                }
                return places;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            log.error("네이버 API 응답 파싱 중 오류 발생", e);
            return new ArrayList<>();
        }
    }
}
