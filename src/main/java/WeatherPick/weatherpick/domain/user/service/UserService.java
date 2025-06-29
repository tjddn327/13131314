package WeatherPick.weatherpick.domain.user.service;

import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.domain.review.service.FileService;
import WeatherPick.weatherpick.domain.review.service.S3Service;
import WeatherPick.weatherpick.domain.user.dto.*;
import WeatherPick.weatherpick.domain.user.entity.UserEntity;
import WeatherPick.weatherpick.domain.user.entity.UserRoleType;
import WeatherPick.weatherpick.domain.user.repository.UserRepository;
import WeatherPick.weatherpick.domain.review.repository.ReviewPostRepository;
import WeatherPick.weatherpick.domain.review.repository.ReviewScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final S3Service s3Service;
    private final ReviewPostRepository reviewPostRepository;
    private final ReviewScrapRepository reviewScrapRepository;



    //유저 한 명 생성
    @Transactional
    public void createOneUser(SignRequestDto dto){
        String username = dto.getUsername();
        String password = dto.getPassword();
        String nickname = dto.getNickname();
        String email = dto.getEmail();
        String name = dto.getName();
        String phonenumber = dto.getPhonenumber();

        // 중복 username이 있는지 확인
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        // 중복 email이 있는지 확인
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }


        // 유저에 대한 Entity 생성 : DTO -> Entity 및 추가 정보 set
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(bCryptPasswordEncoder.encode(password));
        entity.setNickname(nickname);
        entity.setRole(UserRoleType.USER);
        entity.setEmail(email);
        entity.setName(name);
        entity.setPhoneNumber(phonenumber);

        //유저 저장
        userRepository.save(entity);
    }
    //내정보 읽어오기
    @Transactional(readOnly = true)
    public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String username) {
        try {
            UserEntity user = userRepository.findByUsername(username).get();
            if (user == null) return GetUserInfoResponseDto.notExistUser();

            return GetUserInfoResponseDto.success(
                    user.getUsername(),
                    user.getNickname(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getProfileImage()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    public ResponseEntity<? super UserInfoResponseDto> getInfoUser(String username){
        Optional<UserEntity> userEntity = Optional.empty();
        try {
            userEntity = userRepository.findByUsername(username);
            if(userEntity.isEmpty()) return UserInfoResponseDto.notExistUser();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UserInfoResponseDto.success(userEntity.get());
    }


    // 유저 모두 읽기
    @Transactional(readOnly = true)
    public List<UserResponseDto> readAllUsers() {

        List<UserEntity> list = userRepository.findAll();

        List<UserResponseDto> dtos = new ArrayList<>();
        for (UserEntity user : list) {
            UserResponseDto dto = new UserResponseDto();
            dto.setUsername(user.getUsername());
            dto.setNickname(user.getNickname());
            dto.setRole(user.getRole().toString());
            dto.setName(user.getName());
            dto.setPhonenumber(user.getPhoneNumber());
            dto.setCreatedate(user.getCreatedate());
            dtos.add(dto);
        }

        return dtos;
    }

    // 유저 한 명 수정
    @Transactional
    public ResponseEntity<? super UpdateUserResponseDto> updateOneUser(UpdateUserRequestDto dto, String username) {
        try {
            UserEntity user = userRepository.findByUsername(username).get();
            if (user == null) return UpdateUserResponseDto.notExistUser();

            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            }

            if (dto.getNickname() != null && !dto.getNickname().isEmpty()) {
                user.setNickname(dto.getNickname());
            }

            if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
                user.setEmail(dto.getEmail());
            }

            if (dto.getPhonenumber() != null && !dto.getPhonenumber().isEmpty()) {
                user.setPhoneNumber(dto.getPhonenumber());
            }

            // 프로필 이미지 업로드 처리
            if (dto.getProfileImage() != null && !dto.getProfileImage().isEmpty()) {
                // 기존 프로필 이미지가 있다면 S3에서 삭제
                if (user.getProfileImage() != null) {
                    s3Service.deleteFile(user.getProfileImage());
                }

                // 새 프로필 이미지 업로드
                String imageUrl = s3Service.upload(dto.getProfileImage());
                if (imageUrl != null) {
                    user.setProfileImage(imageUrl);
                }
            }

            userRepository.save(user);

            return UpdateUserResponseDto.success(
                user.getUsername(),
                user.getNickname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getProfileImage()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 유저 한 명 삭제
    @Transactional
    public void deleteOneUser(String username) {
        userRepository.deleteByUsername(username);
    }

    // 유저 로그인 (스프링 시큐리티 형식)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        return User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRole().toString())
                .build();
    }
    // 내가 스크랩한 글 조회
    public ResponseEntity<? super UserScrapResponseDto> getMyScraps(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()) return UserScrapResponseDto.notExistUser();

        try {
            List<UserScrapResponseDto.ScrapInfo> scraps = reviewScrapRepository.findByUsername(username).stream()
                    .map(scrap -> new UserScrapResponseDto.ScrapInfo(
                            scrap.getReviewPostId(),
                            reviewPostRepository.findById(scrap.getReviewPostId())
                                    .map(post -> post.getTitle())
                                    .orElse("삭제된 게시글")
                    ))
                    .collect(Collectors.toList());
            return UserScrapResponseDto.success(scraps);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }


    }

}
