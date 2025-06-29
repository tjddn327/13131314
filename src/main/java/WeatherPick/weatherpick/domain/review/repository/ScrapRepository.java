package WeatherPick.weatherpick.domain.review.repository;

import WeatherPick.weatherpick.domain.review.entity.ReviewScrapEntity;
import WeatherPick.weatherpick.domain.review.entity.ScrapPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<ReviewScrapEntity, ScrapPk> {
    ReviewScrapEntity findByReviewPostIdAndUsername(Long ReviewPostId, String username);
    List<ReviewScrapEntity> findByReviewPostId(Long reviewPostId);
}
