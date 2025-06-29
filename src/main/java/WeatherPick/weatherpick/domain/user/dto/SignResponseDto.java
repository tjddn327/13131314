package WeatherPick.weatherpick.domain.user.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignResponseDto extends ResponseDto {

    private SignResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
    }

    public static ResponseEntity<SignResponseDto>success(){
        SignResponseDto result = new SignResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto>duplicateEmail(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL,ResponseMassage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto>duplicateId(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_ID,ResponseMassage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
