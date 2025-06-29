package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class ImageUrlListResponseDto extends ResponseDto {
    private List<String> imageUrls;

    private ImageUrlListResponseDto(List<String> imageUrls) {
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.imageUrls = imageUrls;
    }

    public static ResponseEntity<ImageUrlListResponseDto> success(List<String> imageUrls) {
        ImageUrlListResponseDto result = new ImageUrlListResponseDto(imageUrls);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistImages() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_IMAGES, ResponseMassage.NOT_EXIST_IMAGES);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
} 