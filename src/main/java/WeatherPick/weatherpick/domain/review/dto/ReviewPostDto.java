package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class ReviewPostDto extends ResponseDto {
    private String title;
    private String content;
    private List<String> placeList;

    public ReviewPostDto() {super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);}

    public ReviewPostDto(String title, String content,List<String> placeList) {
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.title = title;
        this.content = content;
        this.placeList = placeList;
    }

    public static ResponseEntity<ReviewPostDto> success(){
        ReviewPostDto result = new ReviewPostDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_USER,ResponseMassage.NOT_EXIDSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }



}

