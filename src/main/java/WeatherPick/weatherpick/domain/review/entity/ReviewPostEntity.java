package WeatherPick.weatherpick.domain.review.entity;

import WeatherPick.weatherpick.domain.place.entity.NaverPlaceEntity;
import WeatherPick.weatherpick.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 리뷰 게시글 엔티티
/*
post_id (PK)	  BIGINT	    게시글 ID
user_id (FK)	  BIGINT	    작성자
place_id (FK)	  BIGINT	    리뷰한 장소
title	          VARCHAR	    게시글 제목
content	        TEXT	      게시글 내용
rating         	INT (1~5)	  장소에 대한 위치 점수
createddate	    DATETIME	  작성 시간
*/

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewPostId")
    private Long reviewPostId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NaverPlaceEntity> places = new ArrayList<>();

    @OneToMany(mappedBy = "reviewPostEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();

    @Column
    private int likeCount=0;

    @Column
    private int scrapCount = 0;
    @Column
    private int viewCount =0;

    @Column
    private int commentCount =0;

    @Column
    String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date.from(Instant.now()));


    public void increaseViewCount(){
        this.viewCount++;
    }
    public void increaseFavoriteCount(){
        this.likeCount++;
    }
    public void decreaseFavoriteCount(){
        this.likeCount--;
    }
    public void increaseScrapCount(){
        this.scrapCount++;
    }
    public void decreaseScrapCount(){this.scrapCount--;}
    public void increaseCommentCount(){
        this.commentCount++;
    }
    public void decreaseCommentCount(){
        this.commentCount--;
    }

    public void addPlace(NaverPlaceEntity place) {
        places.add(place);
        place.setReview(this);
    }

    public void removePlace(NaverPlaceEntity place) {
        places.remove(place);
        place.setReview(null);
    }
}
