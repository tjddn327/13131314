package WeatherPick.weatherpick.domain.place.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NaverSearchResponseDto {
    @JsonProperty("items")
    private List<NaverPlaceDto> items;
    
    @JsonProperty("total")
    private int total;
    
    @JsonProperty("start")
    private int start;
    
    @JsonProperty("display")
    private int display;
    
    @JsonProperty("lastBuildDate")
    private String lastBuildDate;
    
    @JsonProperty("errorMessage")
    private String errorMessage;
    
    @JsonProperty("errorCode")
    private String errorCode;
    
    @JsonProperty("status")
    private String status;
}
