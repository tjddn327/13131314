package WeatherPick.weatherpick.domain.review.entity;

import WeatherPick.weatherpick.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

//리뷰 댓글
/*
필드명	          타입	    설명
comment_id (PK)	BIGINT	  댓글 ID
post_id (FK)	  BIGINT	  소속 리뷰 게시글
user_id (FK)	  BIGINT	  작성 유저
content	        TEXT	    댓글 내용
createddate	    DATETIME	작성 시간
 */

@Entity
@Getter
@Setter
public class ReviewCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewCommentId")
    private Long reviewCommentId;

    @ManyToOne
    @JoinColumn(name = "reviewPostId", nullable = false)
    private ReviewPostEntity reviewPostId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @UpdateTimestamp
    private LocalDateTime writeDateTime;


}
