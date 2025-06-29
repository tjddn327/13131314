package WeatherPick.weatherpick.domain.place.dto;

import WeatherPick.weatherpick.domain.place.entity.NaverPlaceEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NPlaceDto {
    private String title;
    private String address;
    private String roadAddress;
    private String mapx;
    private String mapy;
    private String category;
    private String link;

    public static NPlaceDto from(NaverPlaceEntity entity) {
        NPlaceDto dto = new NPlaceDto();
        dto.setTitle(entity.getTitle());
        dto.setAddress(entity.getAddress());
        dto.setRoadAddress(entity.getRoadAddress());
        dto.setMapx(entity.getMapx());
        dto.setMapy(entity.getMapy());
        dto.setCategory(entity.getCategory());
        dto.setLink(entity.getLink());
        return dto;
    }
} 