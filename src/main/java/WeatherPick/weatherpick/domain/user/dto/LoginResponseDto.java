package WeatherPick.weatherpick.domain.user.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter


public class LoginResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;

    private LoginResponseDto(String token){
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<LoginResponseDto> success(String token){
        LoginResponseDto result = new LoginResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> LoginFail(){
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL,ResponseMassage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }


}
