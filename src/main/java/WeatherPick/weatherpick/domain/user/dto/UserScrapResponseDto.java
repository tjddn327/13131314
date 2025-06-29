package WeatherPick.weatherpick.domain.user.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserScrapResponseDto extends ResponseDto {
    private List<ScrapInfo> scraps;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrapInfo {
        private Long reviewPostId;
        private String title;
    }

    private UserScrapResponseDto(List<ScrapInfo> scraps) {
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.scraps = scraps;
    }

    public static ResponseEntity<UserScrapResponseDto> success(List<ScrapInfo> scraps) {
        UserScrapResponseDto result = new UserScrapResponseDto(scraps);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_USER, ResponseMassage.NOT_EXIDSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
} 