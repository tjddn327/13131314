package WeatherPick.weatherpick.domain.place.entity;

import WeatherPick.weatherpick.domain.review.entity.ReviewPostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "naver_place")
public class NaverPlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String link;

    @Column
    private String category;

    @Column(nullable = false)
    private String address;

    @Column
    private String roadAddress;

    @Column
    private String mapx;

    @Column
    private String mapy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review")
    private ReviewPostEntity review;
} 