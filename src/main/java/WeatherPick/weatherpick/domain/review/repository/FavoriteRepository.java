package WeatherPick.weatherpick.domain.review.repository;

import WeatherPick.weatherpick.domain.review.entity.FavoritePk;
import WeatherPick.weatherpick.domain.review.entity.ReviewFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<ReviewFavoriteEntity, FavoritePk> {
    ReviewFavoriteEntity findByReviewPostIdAndUsername(Long ReviewPostId, String username);
    List<ReviewFavoriteEntity> findByReviewPostId(Long reviewPostId);
}
