package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.domain.review.repository.GetReviewCommentResultSet;
import WeatherPick.weatherpick.domain.review.repository.GetReviewPostResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentListItem {
    private String nickName;
    private String content;
    private String writeDateTime;
    private String profileImage;

    public ReviewCommentListItem(GetReviewCommentResultSet resultSet){
        this.nickName = resultSet.getNickname();
        this.content = resultSet.getContent();
        this.writeDateTime = resultSet.getWriteDateTime();
        this.profileImage = resultSet.getProfileImage();
    }
    public static List<ReviewCommentListItem> copyList(List<GetReviewCommentResultSet> resultSets){
        List<ReviewCommentListItem> list = new ArrayList<>();
        for(GetReviewCommentResultSet resultSet: resultSets){
            ReviewCommentListItem reviewCommentListItem = new ReviewCommentListItem(resultSet);
            list.add(reviewCommentListItem);
        }
        return list;
    }
}
