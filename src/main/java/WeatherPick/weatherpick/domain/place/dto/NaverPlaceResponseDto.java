package WeatherPick.weatherpick.domain.place.dto;

import lombok.Data;
import java.util.List;

@Data
public class NaverPlaceResponseDto {
    private String status;
    private Meta meta;
    private List<Place> places;

    @Data
    public static class Meta {
        private int totalCount;
        private int page;
        private int count;
    }

    @Data
    public static class Place {
        private String name;
        private String roadAddress;
        private String address;
        private String x;
        private String y;
        private String distance;
    }
} 