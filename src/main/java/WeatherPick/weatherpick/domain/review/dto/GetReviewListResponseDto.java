package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import WeatherPick.weatherpick.domain.review.entity.ReviewPostEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetReviewListResponseDto extends ResponseDto {
    private List<ReviewListItem> reviewListItems;

    private GetReviewListResponseDto(List<ReviewPostEntity> reviewPostEntities){
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.reviewListItems = ReviewListItem.getList(reviewPostEntities);
    }
    public static ResponseEntity<GetReviewListResponseDto> success(List<ReviewPostEntity> reviewPostEntities){
        GetReviewListResponseDto result = new GetReviewListResponseDto(reviewPostEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
