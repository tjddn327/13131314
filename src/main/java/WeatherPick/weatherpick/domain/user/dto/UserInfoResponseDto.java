package WeatherPick.weatherpick.domain.user.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import WeatherPick.weatherpick.domain.user.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
public class UserInfoResponseDto extends ResponseDto {
    private String username;
    private String nickname;
    private String prfileImage;

    private UserInfoResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.username = userEntity.getUsername();
        this.nickname = userEntity.getNickname();
        this.prfileImage = userEntity.getProfileImage();
    }
    public static ResponseEntity<UserInfoResponseDto> success(UserEntity userEntity){
        UserInfoResponseDto result = new UserInfoResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_USER,ResponseMassage.NOT_EXIDSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
