package WeatherPick.weatherpick.domain.review.entity;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScrapPk {

    @Column(name = "username")
    private String username;
    @Column(name = "reviewPostId")
    private Long reviewPostId;
}
