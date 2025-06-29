package WeatherPick.weatherpick.domain.review.repository;

import WeatherPick.weatherpick.domain.review.entity.ReviewScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewScrapRepository extends JpaRepository<ReviewScrapEntity, String> {
    List<ReviewScrapEntity> findByUsername(String username);
} 