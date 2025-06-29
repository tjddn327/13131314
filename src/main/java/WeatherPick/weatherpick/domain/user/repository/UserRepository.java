package WeatherPick.weatherpick.domain.user.repository;

import WeatherPick.weatherpick.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    // 아이디 중복 검사 메서드
    boolean existsByUsername(String username);

    // 이메일 중복 검사 메서드
    boolean existsByEmail(String email);

    //username으로 유저 불러오기
    Optional<UserEntity> findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

    // 닉네임 중복 검사 메서드
    boolean existsByNickname(String nickname);

}
