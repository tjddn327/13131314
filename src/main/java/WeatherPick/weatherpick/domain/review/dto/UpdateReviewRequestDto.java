package WeatherPick.weatherpick.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateReviewRequestDto {
    @NotBlank
    private String title;
    
    @NotBlank
    private String content;
    
    private List<ReviewPostRequestDto.PlaceDto> places;
} 