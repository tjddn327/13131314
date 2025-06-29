package WeatherPick.weatherpick.domain.user.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class UpdateUserResponseDto extends ResponseDto {
    private String username;
    private String nickname;
    private String email;
    private String phonenumber;
    private String profileImage;

    private UpdateUserResponseDto(String username, String nickname, String email, String phonenumber, String profileImage) {
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.profileImage = profileImage;
    }

    public static ResponseEntity<UpdateUserResponseDto> success(String username, String nickname, String email, String phonenumber, String profileImage) {
        UpdateUserResponseDto result = new UpdateUserResponseDto(username, nickname, email, phonenumber, profileImage);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_USER, ResponseMassage.NOT_EXIDSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

} 