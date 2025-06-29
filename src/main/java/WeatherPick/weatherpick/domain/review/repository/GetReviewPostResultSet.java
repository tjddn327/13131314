package WeatherPick.weatherpick.domain.review.repository;

public interface GetReviewPostResultSet {
    Long getReviewPostId();
    String getTitle();
    String getContent();
    String getWriteDateTime();
    String getWriterNickname();
    String getWriterProfileImage();
    Integer getLikeCount();
    Integer getScrapCount();
    Integer getViewCount();
    Integer getCommentCount();

    String getCreateDate();
}
