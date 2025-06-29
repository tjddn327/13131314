package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PutScrapResponseDto extends ResponseDto{
    private PutScrapResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
    }
    public static ResponseEntity<PutScrapResponseDto> success(){
        PutScrapResponseDto result = new PutScrapResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistReview(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_REVIEW,ResponseMassage.NOT_EXIDSTED_REVIEW);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_USER,ResponseMassage.NOT_EXIDSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
