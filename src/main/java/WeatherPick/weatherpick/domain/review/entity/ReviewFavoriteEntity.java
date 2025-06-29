package WeatherPick.weatherpick.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavoritePk.class)
public class ReviewFavoriteEntity {
    @Id
    String username;
    @Id
    Long reviewPostId;

}
