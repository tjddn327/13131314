package WeatherPick.weatherpick.domain.review.repository;

import WeatherPick.weatherpick.domain.review.entity.ReviewPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewPostRepository extends JpaRepository<ReviewPostEntity, Long> {
    List<ReviewPostEntity> findAllByUser_UserId(Long userId);
    ReviewPostEntity findByReviewPostId(Long reviewPostId);

    @Query("SELECT r FROM ReviewPostEntity r JOIN FETCH r.user")
    List<ReviewPostEntity> findByOrderByWriteDateDesc();
    @Query(
            value =
            "select "+
            "R.reviewPostId AS reviewPostId, "+
            "R.title AS title, "+
            "R.content AS content, "+
            "R.writeDate AS writeDateTime, "+
            "U.nickname AS writerNickname, "+
            "U.profileImage AS writerProfileImage, "+
            "U.username AS writerUsername, "+
            "R.likeCount AS likeCount, "+
            "R.scrapCount AS scrapCount, "+
            "R.commentCount AS commentCount, "+
            "R.ViewCount AS viewCount "+
            "from db1.ReviewPostEntity AS R "+
            "inner join db1.UserEntity AS U "+
            "on R.userId = U.userId "+
            "WHERE reviewPostId = ?1",
            nativeQuery = true
    )
    GetReviewPostResultSet getReview(Long reviewId);



}
