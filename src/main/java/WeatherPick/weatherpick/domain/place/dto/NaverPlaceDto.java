package WeatherPick.weatherpick.domain.place.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverPlaceDto {
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("link")
    private String link;
    
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("roadAddress")
    private String roadAddress;
    
    @JsonProperty("mapx")
    private String mapx;
    
    @JsonProperty("mapy")
    private String mapy;

    public void setTitle(String title) {
        this.title = cleanHtmlTags(title);
    }

    public void setCategory(String category) {
        this.category = cleanHtmlTags(category);
    }

    private String cleanHtmlTags(String text) {
        if (!StringUtils.hasText(text)) {
            return text;
        }
        return text.replaceAll("<[^>]*>", "")
                  .replaceAll("&quot;", "\"")
                  .replaceAll("&amp;", "&")
                  .replaceAll("&lt;", "<")
                  .replaceAll("&gt;", ">")
                  .replaceAll("\\/", "/")
                  .trim();
    }

    @Override
    public String toString() {
        return "NaverPlaceDto{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", category='" + category + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", mapx='" + mapx + '\'' +
                ", mapy='" + mapy + '\'' +
                '}';
    }
}
