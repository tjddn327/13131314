package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.domain.review.entity.ImageEntity;
import WeatherPick.weatherpick.domain.review.entity.ReviewPostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListItem {
    private Long reviewPostId;
    private String title;
    private int favoriteCount;
    private int viewCount;
    private int scrapCount;
    private int commentCount;
    private String writeDateTime;
    private String writerNickname;
    private String writerProfileImage;
    private List<ImageEntity> images;

    public ReviewListItem(ReviewPostEntity reviewPostEntity){
        this.reviewPostId = reviewPostEntity.getReviewPostId();
        this.title = reviewPostEntity.getTitle();
        this.favoriteCount = reviewPostEntity.getLikeCount();
        this.viewCount = reviewPostEntity.getViewCount();
        this.scrapCount = reviewPostEntity.getScrapCount();
        this.commentCount = reviewPostEntity.getCommentCount();
        this.writeDateTime = reviewPostEntity.getWriteDate();
        this.writerNickname = reviewPostEntity.getUser().getNickname();
        this.writerProfileImage = reviewPostEntity.getUser().getProfileImage();
    }
    public static List<ReviewListItem> getList(List<ReviewPostEntity> reviewPostEntities){
        List<ReviewListItem> list = new ArrayList<>();
        for(ReviewPostEntity reviewPostEntity: reviewPostEntities){
            ReviewListItem reviewListItem = new ReviewListItem(reviewPostEntity);
            list.add(reviewListItem);
        }
        return list;
    }
}
