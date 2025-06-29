package WeatherPick.weatherpick.domain.user.service;

import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.domain.user.dto.LoginRequestDto;
import WeatherPick.weatherpick.domain.user.dto.LoginResponseDto;
import WeatherPick.weatherpick.domain.user.dto.SignRequestDto;
import WeatherPick.weatherpick.domain.user.dto.SignResponseDto;
import WeatherPick.weatherpick.domain.user.entity.UserEntity;
import WeatherPick.weatherpick.domain.user.entity.UserRoleType;
import WeatherPick.weatherpick.domain.user.repository.UserRepository;
import WeatherPick.weatherpick.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //회원가입
    public ResponseEntity<? super SignResponseDto> signUp(SignRequestDto dto){
        try{


            String username = dto.getUsername();
            String password = dto.getPassword();
            String nickname = dto.getNickname();
            String email = dto.getEmail();
            String name = dto.getName();
            String phonenumber = dto.getPhonenumber();

            // 중복 username이 있는지 확인
            if (userRepository.existsByUsername(username)) {
                return SignResponseDto.duplicateId();
            }
            // 중복 email이 있는지 확인
            if (userRepository.existsByEmail(email)) {
                return SignResponseDto.duplicateEmail();
            }
            String encodedPassword =  passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity entity = new UserEntity();
            entity.setUsername(username);
            entity.setPassword(encodedPassword);
            entity.setNickname(nickname);
            entity.setRole(UserRoleType.USER);
            entity.setEmail(email);
            entity.setName(name);
            entity.setPhoneNumber(phonenumber);

            //유저 저장
            userRepository.save(entity);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignResponseDto.success();
    }

    //로그인
    public ResponseEntity<? super LoginResponseDto> Login(LoginRequestDto dto){
        String token =null;
        try {
            String username = dto.getUsername();
            Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if(userEntity.isEmpty()) return LoginResponseDto.LoginFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.get().getPassword();
            boolean isMatched = passwordEncoder.matches(password,encodedPassword);
            if(!isMatched) return LoginResponseDto.LoginFail();

            token = jwtProvider.create(username);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return LoginResponseDto.success(token);
    }


}
