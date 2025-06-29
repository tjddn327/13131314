package WeatherPick.weatherpick.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignRequestDto {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String name;
    private String phonenumber;
}
