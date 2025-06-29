package WeatherPick.weatherpick.domain.place.repository;

import WeatherPick.weatherpick.domain.place.entity.NaverPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaverPlaceRepository extends JpaRepository<NaverPlaceEntity, Long> {
    List<NaverPlaceEntity> findByReview_ReviewPostId(Long reviewId);
} 